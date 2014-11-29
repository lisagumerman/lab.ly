
require.config({
    baseUrl: './js',

    paths: {
        jquery: 'lib/jquery/jquery',
        angular: 'lib/angular/angular',
        dropzone: 'lib/dropzone/dropzone',
        'jquery-ui': 'lib/jquery/ui',
        interact: 'lib/interact/interact'
    },

    shim: {
        'angular' : {
            exports: 'angular',
            deps: ['jquery']
        },
        'interact' : {
            exports: 'interact'
        },
        'dropzone' : {
            exports: 'dropzone',
            deps: ['jquery']
        }
    }

});


window.name = "NG_DEFER_BOOTSTRAP!";

define([
    'lib/ready/ready',
    'jquery',
    'angular',
    'directives/directives',
    'controllers/controllers'
], function(ready,
            $, angular,
            directives, controllers) {

    var Lably = angular.module('Lably', [
        'Lably.Controllers',
        'Lably.Directives'
    ]);

    ready(function() {
        var element = $('#application-main');
        angular.bootstrap(element,  ['Lably']);
    });

});
