define(['jquery', 'angular'], function($, angular){

    var Controllers = angular.module('lably.controllers', []);
//    Controllers.controller('LablyMain', ['$scope', function($scope){
//        $scope.whatever = "Hello";
//    }]);
    var lably = angular.module('Lably', []);
    lably.controller(['$scope', function($scope) {
        $scope.whatever = "Hello :)";


    }]);
    return lably;



});