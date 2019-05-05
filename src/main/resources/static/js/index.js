const lossesInRadiusUrl = "/api/findings";
const lossesRadiusKm = 100;

const colorImgUrlMap = new Map([
    ['yellow',  '../img/yellow.png'],
    ['blue',    '../img/blue.png'],
    ['red',    '../img/red.png']
]);

let findings = [];

let service;

$(function () {

    $('.ui.dropdown')
        .dropdown();

    $('.filter-finding-group').on('click', function (e) {
        let g = $(this).attr('value');
        filterMarkersOfFindingGroup(g);
    });

    $('.filter-finding-group').each(function () {
        $(this).children(".ui.circular.label").addClass(getRandomColor());
    })
});

function filterMarkersOfFindingGroup(group) {
    if (group === 'ALL') {
        findings.forEach(l => l.marker.setMap(map));
    } else {
        findings.forEach(l => {
            if (l.group === group) {
                l.marker.setMap(map);
            } else {
                l.marker.setMap(null);
            }
        });
    }
}

function initMap() {

    map = new google.maps.Map(document.getElementById('map'), {
        zoom: mapZoom,
        minZoom: 13,
        maxZoom: 20,
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
}

function moveLocationSearchControl() {
    let wrapper = document.getElementById('map-controls-wrapper');
    let input = document.getElementById('address-in');

    searchBox = new google.maps.places.SearchBox(input);
    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(wrapper);
}

function loadLossesInRadius(location, radius) {

    let query = lossesInRadiusUrl + "?pivotLat=" + location.lat() + "&pivotLng=" + location.lng() + "&radius=" + radius;

    $.get(query, function (inFindings) {
        $.each(inFindings, function (index, inFinding) {

                let finding = {
                    group: inFinding.findingGroupName,
                    marker: new google.maps.Marker({
                        position: new google.maps.LatLng(inFinding.latitude, inFinding.longitude),
                        title: inFinding.name,
                        url: '/findings/' + inFinding.id,
                        animation: google.maps.Animation.DROP
                    })
                };

                let color = getColorNameForFindingWithAge(inFinding.daysOld);
                let colorImgUrl = colorImgUrlMap.get(color);
                if(colorImgUrl !== undefined){
                    finding.marker.setIcon(colorImgUrl);
                }

                let existingMarker = findings.find(l => areLossMarkersEqual(l.marker, finding.marker));
                if (existingMarker === undefined) {

                    finding.marker.setMap(map);
                    findings.push(finding);

                    let findingInfoWindow = new google.maps.InfoWindow({
                        content: buildFindingInfoWindowContent(inFinding)
                    });

                    finding.marker.addListener('click', function () {
                        window.location.href = this.url;
                    });

                    finding.marker.addListener('mouseover', function () {
                        findingInfoWindow.open(map, this);
                    });

                    finding.marker.addListener('mouseout', function () {
                        findingInfoWindow.close();
                    });
                }
            }
        );
    }).fail(function () {
        alert("Failed to upload findings in radius.");
    })
}

function buildFindingInfoWindowContent(finding) {
    return " <div class=\"ui card\">\n" +
        "        <div class=\"content\">\n" +
        "            <div class=\"header\">\n" +
        finding.name +
        "            </div>\n" +
        "            <div class=\"meta\">\n" +
        finding.dateFound +
        "            </div>\n" +
        "            <div class=\"description\">\n" +
        finding.description +
        "            </div>\n" +
        "        </div>\n" +
        "    </div>"
}
