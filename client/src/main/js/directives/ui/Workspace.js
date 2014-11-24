define([
    'angular',
    'jquery',
    'jquery-ui/draggable',
    'dropzone',
], function(angular, $, draggable, dropzone){

    return function() {
        return {
            templateUrl: 'html/templates/workspace.html',
            restrict: 'A',
            replace: 'true',
            link:function(scope, element, attributes) {
                var dz = $(element).find('.upload-file:first')[0]
                $(dz).dropzone({
                    url: 'http://localhost:8080/web/lably/upload/upload'
                });
            }
        };
    }

});