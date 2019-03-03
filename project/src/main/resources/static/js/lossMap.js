let map, marker, infowindow;

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 17,
        mapTypeControl: false,
        zoomControl: true,
        scaleControl: true,
        streetViewControl: false,
        fullscreenControl: false
    });

    autocomplete = new google.maps.places.Autocomplete(
        document.getElementById('autocomplete'), {
            types: ['geocode']
        });

    // Create the search box and link it to the UI element.
    var wrapper = document.getElementById('autocomplete-wrapper');
    var input = document.getElementById('autocomplete');
    var searchBox = new google.maps.places.SearchBox(input);
    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(wrapper);

    // Bias the SearchBox results towards current map's viewport.
    map.addListener('bounds_changed', function () {
        searchBox.setBounds(map.getBounds());
    });

    focusOnCurrentLocation();
}

function focusOnCurrentLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };

            map.setCenter(pos);
        }, function () {
            handleLocationError(true, infoWindow, map.getCenter());
        });
    } else {
        // Browser doesn't support Geolocation
        handleLocationError(false, infoWindow, map.getCenter());
    }
}


function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
        'Error: The Geolocation service failed.' :
        'Error: Your browser doesn\'t support geolocation.');
    infoWindow.open(map);
}