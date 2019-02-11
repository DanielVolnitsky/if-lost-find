$(function () {
    var map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 50.519306, lng: 30.243572},
        zoom: 17,
        streetViewControl: false,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    var locationInputBlock = document.getElementById('pac-card');
    var locationInput = document.getElementById('location-input');

    map.controls[google.maps.ControlPosition.TOP_RIGHT].push(locationInputBlock);

    var autocomplete = new google.maps.places.Autocomplete(locationInput);

    // Bind the map's bounds (viewport) property to the autocomplete object,
    // so that the autocomplete requests use the current map bounds for the
    // bounds option in the request.
    autocomplete.bindTo('bounds', map);

    // Set the data fields to return when the user selects a place.
    autocomplete.setFields(
        ['address_components', 'geometry', 'icon', 'name']);

    var locationInfoWindow = new google.maps.InfoWindow();
    var locationInfoWindowContent = document.getElementById('infowindow-content');

    locationInfoWindow.setContent(locationInfoWindowContent);
    var marker = new google.maps.Marker({
        map: map,
        anchorPoint: new google.maps.Point(0, -29)
    });

    autocomplete.addListener('place_changed', function () {
        locationInfoWindow.close();
        marker.setVisible(false);
        var place = autocomplete.getPlace();
        if (!place.geometry) {
            // User entered the name of a Place that was not suggested and
            // pressed the Enter key, or the Place Details request failed.
            window.alert("No details available for input: '" + place.name + "'");
            return;
        }

        // If the place has a geometry, then present it on a map.
        if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
        } else {
            map.setCenter(place.geometry.location);
            map.setZoom(17);  // Why 17? Because it looks good.
        }
        marker.setPosition(place.geometry.location);
        marker.setVisible(true);

        var address = '';
        if (place.address_components) {
            address = [
                (place.address_components[0] && place.address_components[0].short_name || ''),
                (place.address_components[1] && place.address_components[1].short_name || ''),
                (place.address_components[2] && place.address_components[2].short_name || '')
            ].join(' ');
        }

        locationInfoWindowContent.children['place-icon'].src = place.icon;
        locationInfoWindowContent.children['place-name'].textContent = place.name;
        locationInfoWindowContent.children['place-address'].textContent = address;
        locationInfoWindow.open(map, marker);
    });
});