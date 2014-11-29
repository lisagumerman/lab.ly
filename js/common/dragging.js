/*var workspaceInteract = interact('.workspace').dropzone({
accept: '.widget.draggable'
});*/


var widgetInteract = interact('.widget.draggable')
    .restrict({
        drag: 'parent',
        endOnly: false,
        elementRect: { top: 0, left: 0, bottom: 1, right: 1 }
    })
    .snap({
        mode: 'grid',
        range: Infinity,
        actions: ['drag',  'resizexy'],
        grid: {x: 25, y: 25 },
        elementOrigin: {x: 0, y: 0 }
    })
    .resizeable({
        onmove: function(event) {
            var target = event.target;
            var
      newWidth  = parseFloat(target.style.width ) + event.dx,
      newHeight = parseFloat(target.style.height) + event.dy;
    target.style.width  = newWidth + 'px';
    target.style.height = newHeight + 'px';
        }
    })
    .draggable({
        max:infinity,
        onmove: function (event) {
            var target = event.target,
                x = (parseFloat(target.getAttribute('data-x')) || 0) + event.dx,
                y = (parseFloat(target.getAttribute('data-y')) || 0) + event.dy;
            target.style.webkitTransform =
            target.style.transform =
                'translate(' + x + 'px, ' + y + 'px)';
            target.setAttribute('data-x', x);
            target.setAttribute('data-y', y);
        }
    });
