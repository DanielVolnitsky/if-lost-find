$(document).ready(function() {
    $('#range').range({
        min: 0,
        max: 100,
        start: 0,
        onChange: function(value) {
            $('#display').html(value);
        }
    });

    $('#range').addClass('disabled');
    $('#radius-val-wrapper').hide();
    $('#radius-btn').addClass('disabled');


    $('#radius-cbx').checkbox({
        onChecked: function() {
            $('#range').removeClass('disabled');
            $('#radius-val-wrapper').show();
            $('#radius-btn').removeClass('disabled');
        },
        onUnchecked: function() {
            $('#range').addClass('disabled');
            $('#radius-val-wrapper').hide();
            $('#radius-btn').addClass('disabled');
        }
    });
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
