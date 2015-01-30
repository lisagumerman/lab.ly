
require.config({
    baseUrl: './js',

    paths: {
        jquery: 'lib/jquery/jquery',
        angular: 'lib/angular/angular',
        dropzone: 'lib/dropzone/dropzone',
        'jquery-ui': 'lib/jquery/ui',
        lodash: 'lib/lodash/lodash',
        interact: 'lib/interact/interact',
        datatables: 'lib/datatables/datatables.js/jquery.dataTables'
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
    'controllers/controllers',
    'services/services'
], function(ready,
            $, angular,
            directives, controllers,services) {

    var Lably = angular.module('Lably', [
        'Lably.Controllers',
        'Lably.Directives',
        'Lably.Services'
    ]);

    ready(function() {
        var element = $('#application-main');
        angular.bootstrap(element,  ['Lably']);
    });

});
