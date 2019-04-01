// Start the main app logic.
requirejs(['jquery', './test_inner'],
    function   ($, ti) {
        //jQuery, canvas and the app/sub module are all
        //loaded and can be used here now.

        alert($('#a').val() + ' ' + ti);
    });