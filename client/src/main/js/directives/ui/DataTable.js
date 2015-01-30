define([
    'angular',
    'jquery',
    'datatables',
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
            require: '^widget',

            templateUrl: 'html/templates/widgets/table.html',
            link:function(scope, element, attributes) {
                var table = $(element).
                    find('table:first-child');
                $(table).DataTable({
                    lengthChange: false,
                    searching: false,
                    aaData: scope.data,
                    aoColumns:scope.columns
                });
            }
        };
    }
});