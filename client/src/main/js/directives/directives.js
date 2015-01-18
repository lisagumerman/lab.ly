define([
    'angular',
    'directives/ui/Workspace',
    'directives/ui/DataTable',
    'directives/ui/FileUpload'
], function(angular, Workspace, DataTable, DropZone){

    var Directives = angular.module('Lably.Directives', []);
    Directives.directive('workspace', Workspace);
    Directives.directive('datatable', DataTable);
    Directives.directive('dropzone', DropZone);
    return Directives;
});
