const mapZoom = 17;
const mapType = 'roadmap';

function processCurrentLocation(successCallback) {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(successCallback, function () {
            alert("Failed to obtain geolocation information.");
        });
    } else {
        alert("Browser doesn't support Geolocation.");
    }
}