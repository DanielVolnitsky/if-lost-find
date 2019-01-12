function initMap() {

    let planetarium = {
        lat: 50.431782,
        lng: 30.516382
    };

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


    let marker = new google.maps.Marker({
        // position: planetarium,
        map: map,
        draggable: true,
        animation: google.maps.Animation.DROP,
        // title: "Planetarium",
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
    });

}

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
});