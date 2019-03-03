let map, lossMarker, searchBox;

//TODO return current location logic
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

    lossMarker = new google.maps.Marker({
        position: initialPosition,
        draggable: true,
        map: map
    });

    moveLocationSearchControl();
    setLossMarkerEvents();
    setMapEvents();
    setSearchBoxEvents();

    // focusOnCurrentLocation();
    loadAllLosses();
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
    lossMarker.addListener('dragend', function (evt) {
        setLocationData(evt.latLng);
        document.getElementById('current').innerHTML = '<p>Marker dropped: Current Lat: ' + evt.latLng.lat() + ' Current Lng: ' + evt.latLng.lng() + '</p>';
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
    });
}

function moveLocationSearchControl() {
    let wrapper = document.getElementById('autocomplete-wrapper');
    let input = document.getElementById('autocomplete');
    searchBox = new google.maps.places.SearchBox(input);
    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(wrapper);
}

function loadAllLosses() {

    $.get("http://localhost:8080/api/losses", function (losses) {
        $.each(losses, function (index, loss) {

            new google.maps.Marker({
                position: new google.maps.LatLng(loss.latitude, loss.longitude),
                map: map,
                title: loss.name,
            });
        });

    }).fail(function () {
        alert("error");
    })
}

function moveMarkerToLocation(location) {
    lossMarker.setMap(null);

    lossMarker = new google.maps.Marker({
        position: location,
        map: map,
        draggable: true
    });

    setLossMarkerEvents();
    setLocationData(location);
}

function setLocationData(location) {
    $('#loss-lat').val(location.lat());
    $('#loss-lng').val(location.lng());
}

function focusOnCurrentLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {

            map.setCenter(position);
            moveMarkerToLocation(position);

        }, function () {
            alert("Failed to obtain geolocation information.");
        });
    } else {
        alert("Browser doesn't support Geolocation.");
    }
}

$(function () {
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
