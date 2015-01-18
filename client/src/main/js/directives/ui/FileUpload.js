define([
    'angular',
    'jquery',
    'dropzone',
    'ui/Draggable',
    'ui/Resizable'
], function(angular,
            $,
            datatables,
            Draggable,
            Resizable){

    return function() {
        return {
            restrict: 'E',
            replace: 'true',
            templateUrl: 'html/templates/widgets/file-upload.html',
            link:function(scope, element, attributes) {
                $(element).dropzone({
                    url: 'http://localhost:8080/web/rest/api/upload/file',
                    success: function (data, result) {

                    }
                });
            }
        };
    }
});