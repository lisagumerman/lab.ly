define([
    'angular',
    'jquery',
    'interact'
], function(angular, $, interact){

    return function() {
        return {
            restrict: 'A',
            replace: 'true',
            link:function(scope, element, attributes) {
                var x = 0, y = 0;
                interact($(element).get(0))
                    .draggable(true)
                    .snap({
                        mode: 'grid',
                        grid: { x: 25, y: 25 },
                        range: Infinity,
                        elementOrigin: { x: 0, y: 0 }
                    })
                    .on('dragmove', function (event) {
                        x += event.dx;
                        y += event.dy;

                        event.target.style.webkitTransform =
                            event.target.style.transform =
                                'translate(' + x + 'px, ' + y + 'px)';
                    })
                    .inertia(true)
                    .restrict({
                        drag: element.parentNode,
                        elementRect: { top: 0, left: 0, bottom: 1, right: 1 },
                        endOnly: true
                    });

//.snap({ mode: 'grid', actions: ['drag',  'resizexy'], grid: {x: 25, y: 25 }, elementOrigin: {x: 0, y: 0 } });
//.restrict({ drag: 'parent', endOnly: false, elementRect: { top: 0, left: 0, bottom: 1, right: 1 } })
//.resizable({ onmove: function(event) { var target = event.target; var newWidth = parseFloat(target.style.width ) + event.dx; var newHeight = parseFloat(target.style.height) + event.dy; target.style.width  = newWidth + 'px'; target.style.height = newHeight + 'px';}})

            }
        };
    }
});