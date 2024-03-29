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
            templateUrl: 'html/templates/widgets/table.html',
            link:function(scope, element, attributes) {
                var table = $(element).
                    find('table:first-child');
                $(table).DataTable({
                    lengthChange: false,
                    searching: false,
                    stripeClasses: [],
                    responsive:true,
                    ordering:false
                });
                Draggable.apply(element);
                Resizable.apply(element);
            }
        };
    }
});