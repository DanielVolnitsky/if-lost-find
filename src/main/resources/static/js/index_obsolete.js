const lossesInRadiusUrl = "/api/findings";
const lossesRadiusKm = 0.8;

// const nearbyLossesUrl = "/api/findings/nearest";
// const nearbyLossesLimit = 5;

let lossMarkers = [];
let service;

function initMap() {

    map = new google.maps.Map(document.getElementById('map'), {
        zoom: mapZoom,
        mapTypeId: mapType,
        mapTypeControl: false,
        zoomControl: true,
        scaleControl: true,
        streetViewControl: false,
        fullscreenControl: false
    });

    moveLocationSearchControl();
    setMapEvents();
    setSearchBoxEvents();

    processCurrentLocation(function (currPosition) {

        let currLocation = new google.maps.LatLng(currPosition.coords.latitude, currPosition.coords.longitude);

        map.setCenter(currLocation);

        loadLossesInRadius(currLocation, lossesRadiusKm);
        // loadNearbyLosses(currLocation, nearbyLossesLimit);

    }, processDefaultLocation);
}

function processDefaultLocation() {

    let defaultLocation = $('#user-default-location').val();
    let request = {
        query: defaultLocation,
        fields: ['name', 'geometry'],
    };

    service = new google.maps.places.PlacesService(map);
    service.findPlaceFromQuery(request, function (results, status) {
        if (status === google.maps.places.PlacesServiceStatus.OK) {

            let place = results[0];
            let location = place.geometry.location;

            map.setCenter(location);

            loadLossesInRadius(location, lossesRadiusKm);
            // loadNearbyLosses(location, nearbyLossesLimit);

        } else {
            alert("Failed to obtain your location info.")
        }
    });
}

function setSearchBoxEvents() {
    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    searchBox.addListener('places_changed', function () {
        let places = searchBox.getPlaces();

        if (places.length === 0) {
            return;
        }

        let place = places[0];
        let bounds = new google.maps.LatLngBounds();

        if (!place.geometry) {
            console.log("Returned place contains no geometry");
            return;
        }

        if (place.geometry.viewport) {
            // Only geocodes have viewport.
            bounds.union(place.geometry.viewport);
        } else {
            bounds.extend(place.geometry.location);
        }

        map.fitBounds(bounds);
        map.setZoom(mapZoom);
    });
}

function setMapEvents() {
    // Bias the SearchBox results towards current map's viewport.
    map.addListener('bounds_changed', function () {
        searchBox.setBounds(map.getBounds());
    });

    map.addListener('idle', function () {
        let center = this.getCenter();

        removeOutOfBoundsLossMarkers(center);
        loadLossesInRadius(this.getCenter(), lossesRadiusKm);
    });
}

//TODO optimize
function removeOutOfBoundsLossMarkers(center) {

    lossMarkers
        .filter(marker => !isMarkerInBounds(marker, center))
        .forEach(marker => marker.setMap(null));

    lossMarkers = lossMarkers.filter(marker => isMarkerInBounds(marker, center));
}

function moveLocationSearchControl() {
    let wrapper = document.getElementById('autocomplete-wrapper');
    let input = document.getElementById('autocomplete');

    searchBox = new google.maps.places.SearchBox(input);
    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(wrapper);
}

function loadLossesInRadius(location, radius) {

    let query = lossesInRadiusUrl + "?pivotLat=" + location.lat() + "&pivotLng=" + location.lng() + "&radius=" + radius;

    $.get(query, function (losses) {

        $.each(losses, function (index, loss) {

            let newLossMarker = new google.maps.Marker({
                position: new google.maps.LatLng(loss.latitude, loss.longitude),
                title: loss.name
            });

            let existingMarker = lossMarkers.find(m => areLossMarkersEqual(m, newLossMarker));
            if (existingMarker === undefined) {

                newLossMarker.setMap(map);
                lossMarkers.push(newLossMarker);

                let lossInfoWindow = new google.maps.InfoWindow({
                    content: buildLossInfoWindowContent(loss)
                });

                newLossMarker.addListener('click', function () {
                    if (isInfoWindowOpen(lossInfoWindow)) {
                        lossInfoWindow.close();
                    } else {
                        lossInfoWindow.open(map, newLossMarker);
                    }
                });

            }
        });

    }).fail(function () {
        alert("Failed to upload findings in radius.");
    })
}

function buildLossInfoWindowContent(loss) {
    return "<div>" +
        "        <h5 class=\"loss-info-head\">" + loss.name + "</h5>" +
        "        <div class=\"loss-info-desc\">" + loss.description + "</div>" +
        "        <a href=\"/finding/" + loss.id + "\" class=\"loss-info-fullinfo-link\">View a full info</a>" +
        "        |" +
        "        <a href=\"/api/found/" + loss.id + "\" class=\"loss-info-found-link\">Report a find</a>" +
        "   </div>"
}

function isMarkerInBounds(marker, center) {
    let checkPoint = {
        lat: marker.getPosition().lat(),
        lng: marker.getPosition().lng()
    };

    let centerPoint = {lat: center.lat(), lng: center.lng()};

    return arePointsInBounds(checkPoint, centerPoint, lossesRadiusKm);
}

function arePointsInBounds(checkPoint, centerPoint, km) {
    let ky = 40000 / 360;
    let kx = Math.cos(Math.PI * centerPoint.lat / 180.0) * ky;
    let dx = Math.abs(centerPoint.lng - checkPoint.lng) * kx;
    let dy = Math.abs(centerPoint.lat - checkPoint.lat) * ky;
    return Math.sqrt(dx * dx + dy * dy) <= km;
}


// function loadNearbyLosses(location, limit) {
//     let query = nearbyLossesUrl + "?pivotLat=" + location.lat() + "&pivotLng=" + location.lng() + "&limit=" + limit;
//
//     $.get(query, function (findings) {
//
//         let wrapper = document.getElementById('nearby-findings-wrapper');
//
//         $.each(findings, function (index, loss) {
//
//             let lossHtml = "<div class=\"card mb-" + limit + " shadow-sm\">\n" +
//                 "                <div class=\"card-header\">\n" +
//                 "                    <h4 class=\"my-0 font-weight-normal\">" + loss.name + "</h4>\n" +
//                 "                </div>\n" +
//                 "                <div class=\"card-body\">\n" +
//                 "                    <h1 class=\"card-title pricing-card-title\">$50</h1>\n" +
//                 "                    <div class=\"mt-3 mb-4\">\n" + loss.description + "</div>\n" +
//                 "                    <button class=\"btn btn-lg btn-block btn-outline-success\" findingGroupName=\"button\">Found It</button>\n" +
//                 "                </div>\n" +
//                 "              </div>"
//
//             wrapper.innerHTML += lossHtml;
//
//         });
//     }).fail(function () {
//         alert("Failed to upload nearby findings.");
//     })
// }


