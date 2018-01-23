requirejs.config({
    baseUrl: 'js',
    paths: {
        // jquery: "https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min",
        // bootstrap: "lib/bootstrap",
        vendor: 'vendor.min',
        // cookie: 'lib/js.cookie',
        elephant: 'elephant.min'
    }
});

requirejs(['vendor', 'elephant'],
    function (vendor, elephant) {
    });