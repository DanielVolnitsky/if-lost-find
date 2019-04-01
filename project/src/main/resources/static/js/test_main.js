define(function (require) {

    let $ = require('jquery');
    let a = $('#a').val();
    console.log("jquery: " + a);

    let ti = require('./test_inner.js');
    console.log('inner_test_func_res: ' + ti);

    console.log("main logic");
});


