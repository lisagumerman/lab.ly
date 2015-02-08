define([
    'angular',
    'jquery',
    'jquery-ui/draggable',
    'dropzone',
    'interact'
], function (angular, $, draggable, dropzone, interact) {

    return function () {
        return {
            templateUrl: 'html/templates/workspace.html',
            restrict: 'A',
            replace: 'false',
            link: function (scope, element, attributes) {

                //$(document).foundation();
                //var dz = $(element).find('.upload-file:first')[0];
                //var el = $(element).find(".workspace-content:first")[0];
                //
                //
                //function addRows(data, body, headers, count) {
                //    for (var i = 0; i < count; ++i) {
                //        var r = $("<tr>");
                //        for (var h in headers) {
                //            var es = data.columns[headers[h]].e[i];
                //            r.append("<td>" + es + "</td>");
                //        }
                //        $(body).append(r);
                //    }
                //}
                //
                //var updateTable = function (data) {
                //    var table = $("<table style='width:60%;'></table>");
                //    var head = $("<thead></thead>");
                //    var body = $("<tbody></tbody>");
                //
                //    var headE = $("<tr></tr>");
                //
                //    for (var header in data.header) {
                //        var headKey = data.header[header];
                //        $(headE).append("<th>" + headKey + "</th>");
                //
                //        var elements = data.columns[headKey].e;
                //        addRows(data, body, data.header, elements.length);
                //
                //    }
                //    $(head).append(headE);
                //
                //    $(table).append(body);
                //    $(table).append(head);
                //
                //    var container = $('<div style="background-color:white; width:600px; height:600px; overflow:auto">');
                //    $(container).append(table);
                //    var x = 0, y = 0;
                //    interact($(container).get(0))
                //        .draggable(true)
                //        .snap({
                //            mode: 'grid',
                //            grid: {x: 25, y: 25},
                //            range: Infinity,
                //            elementOrigin: {x: 0, y: 0}
                //        })
                //        .on('dragmove', function (event) {
                //            x += event.dx;
                //            y += event.dy;
                //
                //            event.target.style.webkitTransform =
                //                event.target.style.transform =
                //                    'translate(' + x + 'px, ' + y + 'px)';
                //        })
                //        .inertia(true)
                //        .restrict({
                //            drag: element.parentNode,
                //            elementRect: {top: 0, left: 0, bottom: 1, right: 1},
                //            endOnly: true
                //        });
                //
                //    $(el).append(container);
                //
                //};
                //
                //
                //$(dz).dropzone({
                //    url: 'http://localhost:8080/web/rest/api/upload/file',
                //    success: function (data, result) {
                //        //$(dz).hide();
                //        updateTable(result);
                //    }
                //});


            }
        };
    }

});
