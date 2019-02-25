let map, marker, infowindow, searchBox;

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 17,
        mapTypeControl: false,
        zoomControl: true,
        scaleControl: true,
        streetViewControl: false,
        fullscreenControl: false
    });

    marker = new google.maps.Marker({
        position: new google.maps.LatLng(50.431782, 30.516382),
        draggable: true
    });

    map.setCenter(marker.position);
    marker.setMap(map);

    infoWindow = new google.maps.InfoWindow;

    autocomplete = new google.maps.places.Autocomplete(
        document.getElementById('autocomplete'), {
            types: ['geocode']
        });

    // Create the search box and link it to the UI element.
    let wrapper = document.getElementById('autocomplete-wrapper');
    let input = document.getElementById('autocomplete');
    searchBox = new google.maps.places.SearchBox(input);
    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(wrapper);

    // Bias the SearchBox results towards current map's viewport.
    map.addListener('bounds_changed', function () {
        searchBox.setBounds(map.getBounds());
    });

    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    searchBox.addListener('places_changed', function () {
        let places = searchBox.getPlaces();

        if (places.length == 0) {
            return;
        }

        let place = places[0];

        marker.setMap(null);

        let bounds = new google.maps.LatLngBounds();

        if (!place.geometry) {
            console.log("Returned place contains no geometry");
            return;
        }

        moveMarkerToLocation(place.geometry.location);

        if (place.geometry.viewport) {
            // Only geocodes have viewport.
            bounds.union(place.geometry.viewport);
        } else {
            bounds.extend(place.geometry.location);
        }

        map.fitBounds(bounds);
    });

    google.maps.event.addListener(map, 'click', function (event) {
        moveMarkerToLocation(event.latLng);
    });

    focusOnCurrentLocation();
}

function focusOnCurrentLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            let pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };

            moveMarkerToLocation(pos);

            map.setCenter(marker.position);
        }, function () {
            handleLocationError(true, infoWindow, map.getCenter());
        });
    } else {
        // Browser doesn't support Geolocation
        handleLocationError(false, infoWindow, map.getCenter());
    }
}

function moveMarkerToLocation(location) {
    marker.setMap(null);

    marker = new google.maps.Marker({
        position: location,
        map: map,
        draggable: true
    });

    setGeolocationData();
}

function setGeolocationData() {
    debugger;
    let location = marker.getPosition();

    let latitude = location.lat();
    let longitude = location.lng();

    $('#loss-lat').val(latitude);
    $('#loss-lng').val(longitude);
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
        'Error: The Geolocation service failed.' :
        'Error: Your browser doesn\'t support geolocation.');
    infoWindow.open(map);
}

$(function() {
    $('#fullpage').fullpage({
        autoScrolling: true,
        scrollHorizontally: false
    });

    $('.ui.form .ui.selection.dropdown').dropdown({
        clearable: true
    });

    $('.clearable.example .ui.inline.dropdown').dropdown({
        clearable: true,
        placeholder: 'any'
    });

    $('select.dropdown').dropdown();

    $('.ui.checkbox').checkbox();

    $("#today-loss-cbx").change(function () {
        let lossDateInput = document.getElementById("loss-date");
        lossDateInput.valueAsDate = this.checked ? new Date() : null;
    });
});
