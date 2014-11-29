define([
    'angular',
    'directives/ui/Workspace',
    'directives/ui/DraggableWidget'
], function(angular, Workspace, Draggable){

    var Directives = angular.module('Lably.Directives', []);
    Directives.directive('workspace', Workspace);
    Directives.directive('draggable', Draggable);



    return Directives;
});
