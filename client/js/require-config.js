require.config({
    baseUrl: '../js',

    paths: {
        angular: 'libs/angular',
        jquery: 'libs/jquery'
    },

    shim : {
        'jquery' : {
             exports: 'jquery'


        },
        'angular': {
            exports: 'angular',
            deps: [
                'jquery'
            ]
        }
    }
});


window.name = "NG_DEFER_BOOTSTRAP!";


define([
    'libs/ready',
    'angular',
    'jquery',
    'application'
], function(
    ready,
    Angular,
    $,
    Lably
    ) {

    require(['libs/ready'], function(domReady){
        domReady(function() {
            Angular.bootstrap(
                $("#main"), ['Lably']
            );
        });
    });



});
