const restrictionCountries = ['ua'];

let autocomplete;

$(document).ready(function () {
    $("form").submit(function () {
        let place = autocomplete.getPlace();
        if (place === undefined) {
            alert("Please choose a valid default location address");
            return false;
        } else if (!place.geometry) {
            alert("Please choose a valid predefined default location address from the suggestions list");
            return false;
        }
    });

    $('.message .close').on('click', function() {
        $(this).closest('.message').transition('fade');
    });
});

function initMap() {
    let input = document.getElementById('location-input');
    autocomplete = new google.maps.places.Autocomplete(input);

    // Set initial restrict to the greater list of countries.
    autocomplete.setComponentRestrictions({
        'country': restrictionCountries
    });

    autocomplete.addListener('place_changed', function () {
        let place = autocomplete.getPlace();
        if (!place.geometry) {
            alert("No details available for input: '" + place.name + "'");
        }
    });
}
