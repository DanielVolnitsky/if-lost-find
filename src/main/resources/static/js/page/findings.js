let geocoder;

$(function () {

    $('.finding-group').each(function () {
        let wrapper = $(this);
        let age = wrapper.attr('finding-age');
        let color = getColorNameForFindingWithAge(age);
        wrapper.addClass(color);
    });

    $('#range').range({
        min: 0,
        max: 100,
        start: 0,
        onChange: function (value) {
            $('#display').html(value);
        }
    });

    $('#range').addClass('disabled');
    $('#radius-val-wrapper').hide();
    $('#radius-btn').addClass('disabled');

    $('#radius-cbx').addClass('disabled');
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

$('.ui.menu .ui.dropdown').dropdown({
    on: 'hover'
});
$('.ui.menu a.item').on('click', function () {
    $(this)
        .addClass('active')
        .siblings()
        .removeClass('active');
});
