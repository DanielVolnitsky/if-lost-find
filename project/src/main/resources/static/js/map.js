const labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';

let map, infowindow, placeSearch;
let labelIndex = 0;

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {
            lat: 50.431782,
            lng: 30.516382
        },
        zoom: 20
    });

    infoWindow = new google.maps.InfoWindow;

<<<<<<< HEAD
    autocomplete = new google.maps.places.Autocomplete(
        document.getElementById('autocomplete'), {
            types: ['geocode']
        });

    // This event listener calls addMarker() when the map is clicked.
    google.maps.event.addListener(map, 'click', function (event) {
        addMarker(event.latLng, map);
    });
}
=======
    let map = new google.maps.Map(
        document.getElementById('map'), {
            zoom: 20,
            center: planetarium,
            streetViewControl: false,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        });

    let geoInput = document.getElementById("geosearch");

    let autocomplete = new google.maps.places.Autocomplete(geoInput);
    autocomplete.bindTo('bounds', map);

    /* let contentString = '<div id="content">' +
            '<div id="siteNotice">' +
            '</div>' +
            '<h1 id="firstHeading" class="firstHeading">Request №12</h1>' +
            '<div id="bodyContent">' +
            '<p><b>Lost Phone</b></p>' +
            '<p>Lost phone near planetarium </p>' +
            '<p>Author: test@test.com</p>' +
            '<p>Attr: Black Phone, without screen</p>' +
            '<p>(Posted 29.02.2018).</p>' +
            '</div>' +
            '</div>';
        let infowindow = new google.maps.InfoWindow({
            content: contentString
        }); */

    let infowindow = new google.maps.InfoWindow();
    let infowindowContent = document.getElementById('infowindow-content');
    infowindow.setContent(infowindowContent);
>>>>>>> c325aac74ca57a86bd5290f2b7df889c3996dd1e

function geolocate() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            let geolocation = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            let circle = new google.maps.Circle({
                center: geolocation,
                radius: position.coords.accuracy
            });
            autocomplete.setBounds(circle.getBounds());
        });
    }
}


// Adds a marker to the map.
function addMarker(location, map) {
    // Add the marker at the clicked location, and add the next-available label
    // from the array of alphabetical characters.
    let marker = new google.maps.Marker({
<<<<<<< HEAD
        position: location,
        label: labels[labelIndex++ % labels.length],
        map: map
    });
}


function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
        'Error: The Geolocation service failed.' :
        'Error: Your browser doesn\'t support geolocation.');
    infoWindow.open(map);
}

$(document).ready(function () {
    $('#fullpage').fullpage({
        autoScrolling: true,
        scrollHorizontally: true
    });

    $('.ui.form .ui.selection.dropdown').dropdown({
        clearable: true
    });

    $('.clearable.example .ui.inline.dropdown').dropdown({
        clearable: true,
        placeholder: 'any'
=======
        // position: planetarium,
        map: map,
        draggable: true,
        animation: google.maps.Animation.DROP,
        icon: '../images/dolma.png',
    });

    marker.addListener('click', () => {
        if (marker.getAnimation() !== null) {
            marker.setAnimation(null);
        } else {
            marker.setAnimation(google.maps.Animation.BOUNCE);
        }

        infowindow.open(map, marker);
    });

    geoInput.addEventListener('click', () => {
        geoInput.value = '';
    });

    document.getElementById("submitIcon").addEventListener('click', () => {
        infowindow.close();
        let place = autocomplete.getPlace();
        if (!place.geometry) {
            return;
        }

        if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
        } else {
            map.setCenter(place.geometry.location);
            map.setZoom(17);
        }

        // Set the position of the marker using the place ID and location.
        marker.setPlace({
            placeId: place.place_id,
            location: place.geometry.location
        });
        marker.setVisible(true);

        infowindowContent.children['place-name'].textContent = place.name;
        infowindowContent.children['place-id'].textContent = place.place_id;
        infowindowContent.children['place-address'].textContent = place.formatted_address;
        infowindow.open(map, marker);
>>>>>>> c325aac74ca57a86bd5290f2b7df889c3996dd1e
    });


<<<<<<< HEAD
    $('select.dropdown').dropdown();
=======
$('.ui.sidebar').sidebar({
    context: $('.bottom.segment')
}).sidebar('attach events', '.menu .menu-item');

$(function () {
    $("#formular").click(function () {
        $(".popyup").modal('show');
    });
    $(".popyup").modal({
        closable: true
    });
});

$('.ui.form .ui.selection.dropdown').dropdown({
    clearable: true
});

$('.clearable.example .ui.inline.dropdown').dropdown({
    clearable: true,
    placeholder: 'any'
>>>>>>> c325aac74ca57a86bd5290f2b7df889c3996dd1e
});