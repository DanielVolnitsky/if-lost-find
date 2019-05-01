let map, findMarker, searchBox, geocoder, findPlaceIdInput;

$(function () {
    $('.ui.form .ui.selection.dropdown').dropdown({
        clearable: true
    });

    $('.clearable.example .ui.inline.dropdown').dropdown({
        clearable: true,
        placeholder: 'any'
    });

    $('select.dropdown').dropdown();

    $('.ui.checkbox').checkbox();

    $("form").submit(function () {
        let group = $('#loss-group-in').val();
        if (group === "") {
            alert('Choose the loss group that is appropriate for your finding!');
            return false;
        }
    });

    findPlaceIdInput = $('#finding-place-id');
});


function initMap() {
    geocoder = new google.maps.Geocoder;

    map = new google.maps.Map(document.getElementById('map'), {
        zoom: mapZoom,
        mapTypeId: mapType,
        mapTypeControl: false,
        zoomControl: true,
        scaleControl: true,
        streetViewControl: false,
        fullscreenControl: false
    });

    findMarker = new google.maps.Marker({
        draggable: true,
        map: map
    });

    moveLocationSearchControl();
    setLossMarkerEvents();
    setMapEvents();
    setSearchBoxEvents();

    processCurrentLocation(function (position) {
        let loc = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);

        map.setCenter(loc);
        findMarker.setPosition(loc);
        setLocationData(loc);
    });
}

function setMapEvents() {
    // Bias the SearchBox results towards current map's viewport.
    map.addListener('bounds_changed', function () {
        searchBox.setBounds(map.getBounds());
    });

    map.addListener('click', function (event) {
        moveMarkerToLocation(event.latLng);
    });
}

function setLossMarkerEvents() {
    findMarker.addListener('dragend', function (evt) {
        setLocationData(evt.latLng);
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

        moveMarkerToLocation(place.geometry.location);

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

function moveLocationSearchControl() {
    let wrapper = document.getElementById('autocomplete-wrapper');
    let input = document.getElementById('autocomplete');
    searchBox = new google.maps.places.SearchBox(input);
    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(wrapper);
}

function moveMarkerToLocation(location) {
    findMarker.setMap(null);

    findMarker = new google.maps.Marker({
        position: location,
        map: map,
        draggable: true
    });

    setLossMarkerEvents();
    setLocationData(location);
}

function setLocationData(location) {
    $('#finding-lat').val(location.lat());
    $('#finding-lng').val(location.lng());
    setPlaceId(location, findPlaceIdInput);
}


