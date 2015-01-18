define([
    'angular',
    'directives/ui/Workspace',
    'directives/ui/DataTable'
], function(angular, Workspace, DataTable){

    var Directives = angular.module('Lably.Directives', []);
    Directives.directive('workspace', Workspace);
    Directives.directive('datatable', DataTable);
    return Directives;
});
