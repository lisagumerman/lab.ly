define([
    'angular',
    'controllers/ui/TabController'
], function(angular, TabController){

    var Controllers = angular.module('Lably.Controllers', []);
    Controllers.controller('Tabs', TabController);

});