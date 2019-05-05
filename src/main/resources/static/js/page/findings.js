let geocoder, userLocation;

$(function () {
    initializeRangeController();
    setRadiusSubmitEvent();
    applyColorsToFindingGroups();

    processCurrentLocation(function (loc) {
        userLocation = {
            lat: loc.coords.latitude,
            lng: loc.coords.longitude
        };
    });

    function setRadiusSubmitEvent() {
        $('#radius-submit').on('click', function () {
            let radius = $('#display').text();

            let href = $(this).attr('href');
            href = href + '?radius=' + radius + '&user-lat=' + userLocation.lat + '&user-lng=' + userLocation.lng;

            $(this).attr('href', href);
        });
    }

    function applyColorsToFindingGroups() {
        $('.finding-group').each(function () {
            let wrapper = $(this);
            let age = wrapper.attr('finding-age');
            let color = getColorNameForFindingWithAge(age);
            wrapper.addClass(color);
        });
    }

    function initializeRangeController() {
        $('#range').range({
            min: 1,
            max: 20,
            start: 1,
            onChange: function (value) {
                $('#display').html(value);
            }
        });
    }
});

function initGoogleMapsApiLogic() {
    geocoder = new google.maps.Geocoder;

    $('.finding-address').each(function () {

        let addressContainer = $(this);
        let addressId = $(this).attr('value');

        geocoder.geocode({'placeId': addressId}, function (results, status) {
            if (status === 'OK' && results[0]) {
                let address = buildAddressByGeocodingResult(results[0]);
                addressContainer.text(address);
            }
        });
    });

    function buildAddressByGeocodingResult(result) {
        let comps = result.address_components;
        let needed = [comps[2].long_name, comps[1].long_name, comps[0].long_name];
        return needed.join(', ')
    }
}