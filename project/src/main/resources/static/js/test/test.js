requirejs.config({
    //By default load any module IDs from js/lib
    baseUrl: '/libs',
    //except, if the module ID starts with "app",
    //load it from the js/app directory. paths
    //config is relative to the baseUrl, and
    //never includes a ".js" extension since
    //the paths config could be for a directory.
    paths: {
        test: '/js/test'
    }
});

// Start the main app logic.
requirejs(['jquery', 'test/test_inner'],
    function   ($, ti) {
        //jQuery, canvas and the app/sub module are all
        //loaded and can be used here now.

        // alert($('#a').val() + ' ' + ti);
    });