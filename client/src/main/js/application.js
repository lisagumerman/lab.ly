
require.config({
    baseUrl: './js',

    paths: {
        jquery: 'lib/jquery/jquery',
        angular: 'lib/angular/angular',
        'jquery-ui': 'lib/jquery/ui'
    },

    shim: {
        'angular' : {
            exports: 'angular',
            deps: ['jquery']
        }
    }

});


window.name = "NG_DEFER_BOOTSTRAP!";

define([
    'lib/ready/ready',
    'jquery',
    'angular',
    'controllers/controllers'
], function(ready, $, angular, controllers) {





//    var Controllers = angular.module('Lably.Controllers', []);
//    Controllers.controller('MyController', ['$rootScope', '$scope',
//        function($rootScope, $scope){
//
//        $scope.person = {
//            firstName: "Lisa",
//            lastName: "Zoomer childe"
//        };
//
//        $scope.saySomething = function() {
//            $rootScope.$broadcast('hello', 'Hi, controller 2!')
//        }
//
//
//    }]);
//
//    Controllers.controller('MyController2', ['$scope', function($scope){
//
//        $scope.person = {
//            firstName: "Joshuaesta",
//            lastName: "Not zoomer child :("
//        };
//        $scope.$on('hello', function(e, args){
//            $scope.person.lastName = args;
//
//        });
//    }]);

    var Lably = angular.module('Lably', ['Lably.Controllers']);

    ready(function() {
        var element = $('#application-main');
        angular.bootstrap(element,  ['Lably']);
    });

});