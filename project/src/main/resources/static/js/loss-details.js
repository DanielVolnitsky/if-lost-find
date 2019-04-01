const mapType = 'roadmap';
const mapZoom = 18;

let map;

function initMap() {

    let lossLat = parseFloat($('#loss-lat').val());
    let lossLng = parseFloat($('#loss-lng').val());

    map = new google.maps.Map(document.getElementById('map'), {
        zoom: mapZoom,
        center: {lat: lossLat, lng: lossLng},
        mapTypeId: mapType,
        mapTypeControl: false,
        zoomControl: true,
        scaleControl: true,
        streetViewControl: false,
        fullscreenControl: false
    });

    let lossMarker = new google.maps.Marker({
        map: map,
        position: {lat: lossLat, lng: lossLng},
        draggable: false
    });
}