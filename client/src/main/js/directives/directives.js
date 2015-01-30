define([
    'angular',
    'directives/ui/Workspace',
    'directives/ui/DataTable',
    'directives/ui/FileUpload',
    'directives/ui/Widget'
], function(angular, Workspace, DataTable, DropZone, Widget){

    var Directives = angular.module('Lably.Directives', []);
    Directives.directive('workspace', Workspace);
    Directives.directive('datatable', DataTable);
    Directives.directive('dropzone', DropZone);
    Directives.directive('widget', Widget);
    return Directives;
});
