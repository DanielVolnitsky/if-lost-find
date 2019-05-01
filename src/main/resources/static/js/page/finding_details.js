let map;

function initMap() {

    let findLat = parseFloat($('#finding-lat').val());
    let findLng = parseFloat($('#finding-lng').val());

    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 17,
        minZoom: 13,
        maxZoom: 20,
        center: {lat: findLat, lng: findLng},
        mapTypeId: 'roadmap',
        mapTypeControl: false,
        zoomControl: true,
        scaleControl: true,
        streetViewControl: false,
        fullscreenControl: false
    });

    let marker = new google.maps.Marker({
        map: map,
        position: {lat: findLat, lng: findLng},
        draggable: false
    });

    marker.addListener('click', function() {
        map.setZoom(17);
        map.setCenter(marker.position);
    });
}