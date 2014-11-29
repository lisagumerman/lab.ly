define([
    'angular',
    'directives/ui/Workspace'
], function(angular, Workspace){

    var Directives = angular.module('Lably.Directives', []);
    Directives.directive('workspace', Workspace);


    return Directives;
});
