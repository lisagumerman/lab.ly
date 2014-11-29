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



            }
        };
    }

});