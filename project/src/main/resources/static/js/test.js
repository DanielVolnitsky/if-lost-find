//Load common code that includes config, then load the app logic for this page.
requirejs(['require_config'], function (common) {
    requirejs(['../js/test_main']);
    requirejs(['../js/test_main2']);
});


