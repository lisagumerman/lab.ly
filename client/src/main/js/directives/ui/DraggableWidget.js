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
                interact(el).draggable();
                //.snap({ mode: 'grid', actions: ['drag',  'resizexy'], grid: {x: 25, y: 25 }, elementOrigin: {x: 0, y: 0 } });
//.restrict({ drag: 'parent', endOnly: false, elementRect: { top: 0, left: 0, bottom: 1, right: 1 } })
//.resizable({ onmove: function(event) { var target = event.target; var newWidth = parseFloat(target.style.width ) + event.dx; var newHeight = parseFloat(target.style.height) + event.dy; target.style.width  = newWidth + 'px'; target.style.height = newHeight + 'px';}})
                }
        };
    }
});