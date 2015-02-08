define(['angular', 'services/ui/Widgets'], function(angular, Widgets){

    var Services = angular.module('Lably.Services', []);

    Services.factory('Widgets', Widgets);
    return Services;



});
