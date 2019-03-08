const nearbyLossesUrl = "/api/losses";
let map, searchBox;

//TODO return current coordinate logic
function initMap() {

    let initialPosition = new google.maps.LatLng(50.455565037464346, 30.473669036770616);

    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 17,
        center: initialPosition,
        mapTypeId: 'roadmap',
        mapTypeControl: false,
        zoomControl: true,
        scaleControl: true,
        streetViewControl: false,
        fullscreenControl: false
    });

    moveLocationSearchControl();

    setMapEvents();
    setSearchBoxEvents();

    // focusOnCurrentLocation();
    loadNearbyLosses(initialPosition, 1);
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
        map.setZoom(17);
    });
}

function setMapEvents() {
    // Bias the SearchBox results towards current map's viewport.
    map.addListener('bounds_changed', function () {
        searchBox.setBounds(map.getBounds());
    });
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

            new google.maps.Marker({
                position: new google.maps.LatLng(loss.latitude, loss.longitude),
                map: map,
                title: loss.name,
            });
        });

    }).fail(function () {
        alert("Failed to upload nearby losses.");
    })
}


