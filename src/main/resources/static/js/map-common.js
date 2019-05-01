const mapZoom = 17;
const mapType = 'roadmap';

function setPlaceId(loc, elementToSet){
    let lat = loc.lat();
    let lng = loc.lng();

    processPlaceFromCoordinates(lat, lng, function(data){
        let id = data.results[0].place_id;
        elementToSet.val(id);
    });
}

function processPlaceFromCoordinates(lat, lng, callback) {
    let key = $('#google-map-key').val();
    let url = 'https://maps.googleapis.com/maps/api/geocode/json?latlng=' + lat + ',' + lng + '&key=' + key + '';

    $.get(url, callback).fail(function () {
        alert("Failed to process reverse geocoding operation in order to obtain the finding location place id.");
    });
}

function processCurrentLocation(geoSupportedCallback, geoNotSupportedCallback) {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(geoSupportedCallback, geoNotSupportedCallback);
    } else {
        alert("Browser doesn't support Geolocation.");
    }
}

function areLossMarkersEqual(m1, m2) {
    let m1Pos = m1.getPosition();
    let m2Pos = m2.getPosition();

    return m1Pos.lat() === m2Pos.lat()
        && m1Pos.lng() === m2Pos.lng()
        && m1.title === m2.title;
}