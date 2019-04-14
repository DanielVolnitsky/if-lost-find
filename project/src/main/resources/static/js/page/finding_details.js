const mapType = 'roadmap';
const mapZoom = 18;

let map;

function initMap() {

    let findLat = parseFloat($('#finding-lat').val());
    let findLng = parseFloat($('#finding-lng').val());

    map = new google.maps.Map(document.getElementById('map'), {
        zoom: mapZoom,
        center: {lat: findLat, lng: findLng},
        mapTypeId: mapType,
        mapTypeControl: false,
        zoomControl: true,
        scaleControl: true,
        streetViewControl: false,
        fullscreenControl: false
    });

    new google.maps.Marker({
        map: map,
        position: {lat: findLat, lng: findLng},
        draggable: false
    });
}