const nearbyLossesUrl = "/api/losses";
const lossesRadiusKm = 0.8;

let lossMarkers = [];

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

    processCurrentLocation(function (position) {
        let loc = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);

        map.setCenter(loc);
        loadNearbyLosses(loc, lossesRadiusKm);
    });
}

function setSearchBoxEvents() {
    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    searchBox.addListener('places_changed', function () {
        let places = searchBox.getPlaces();

        if (places.length == 0) {
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
        loadNearbyLosses(this.getCenter(), lossesRadiusKm);
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

function loadNearbyLosses(location, radius) {

    let query = nearbyLossesUrl + "?pivotLat=" + location.lat() + "&pivotLng=" + location.lng() + "&radius=" + radius;

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
                    if(isInfoWindowOpen(lossInfoWindow)){
                        lossInfoWindow.close();
                    } else {
                        lossInfoWindow.open(map, newLossMarker);
                    }
                });

            }
        });

    }).fail(function () {
        alert("Failed to upload nearby losses.");
    })
}

function buildLossInfoWindowContent(loss) {
    return "<div>" +
        "        <h5 class=\"loss-info-head\">" + loss.name + "</h5>" +
        "        <div class=\"loss-info-desc\">" + loss.description + "</div>" +
        "        <a href=\"/loss/info/" + loss.id + "\" class=\"loss-info-fullinfo-link\">View a full info</a>" +
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


