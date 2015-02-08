define(['interact', 'ui/Draggable', 'ui/Resizable', 'jquery'], function(
    interact,
    Draggable,
    Resizable, $) {

    return function() {
        return {
            restrict: 'E',
            transclude:true,
            replace:true,
            templateUrl: 'html/templates/widgets/widget.html',
            link:function(scope, element, attributes) {
                Draggable.apply(element);
                Resizable.apply(element);
            },
            controller:function() {

            }
        }
    };



});
