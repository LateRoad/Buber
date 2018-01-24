requirejs.config({
    baseUrl: 'vendor',
    paths: {
        jquery: "jquery/jquery.min",
        // // cookie: 'lib/js.cookie',
        // elephant: 'elephant',
        bootstrap: "bootstrap/js/bootstrap.bundle.min",
        jqueryEasing: "jquery-easing/jquery.easing.min"
        // vendor: 'vendor'
    }
    // shim: {
    //     "jquery": ["jquery/jquery"]
    // }
});

requirejs(['jquery', 'bootstrap', 'jqueryEasing', '../js/sb-admin'],
    function ($, bootstrap) {
    });