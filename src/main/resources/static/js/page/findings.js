let geocoder;

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

$(function () {

    $('.finding-group').each(function () {
        $(this).addClass(getRandomBrightColor());
    })

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

$('.ui.menu .ui.dropdown').dropdown({
    on: 'hover'
});
$('.ui.menu a.item').on('click', function () {
    $(this)
        .addClass('active')
        .siblings()
        .removeClass('active');
});
