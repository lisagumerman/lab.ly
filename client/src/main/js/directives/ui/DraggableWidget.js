define([
    'angular',
    'jquery',
    'lib/interact/interact'
], function(angular, $, interact){

    return function() {
        return {
            restrict: 'A',
            replace: 'true',
            link:function(scope, element, attributes) {
                var el = $(element);
                el.draggable();
            }
        };
    }

});