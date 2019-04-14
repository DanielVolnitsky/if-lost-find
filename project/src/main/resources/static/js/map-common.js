const mapZoom = 17;
const mapType = 'roadmap';

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