function focusOnCurrentLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {

            map.setCenter(position);
            moveMarkerToLocation(position);

        }, function () {
            alert("Failed to obtain geolocation information.");
        });
    } else {
        alert("Browser doesn't support Geolocation.");
    }
}
