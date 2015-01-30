define([
    'lodash',
    'angular',
    'jquery',
    'dropzone',
    'util/TableFormatter'
], function(
    _,
    angular,
            $,
            datatables,
            Formatter){

    return ['Widgets', function(Widgets) {

        return {
            restrict: 'E',
            transclude:true,
            replace: 'true',
            require: '^widget',
            scope: {
                onDataLoaded: '&onDataLoaded'
            },
            templateUrl: 'html/templates/widgets/file-upload.html',
            link:function postLink(scope, element, attributes) {
                var f = scope.onDataLoaded;
                $(element).dropzone({
                    url: 'http://localhost:8080/web/rest/api/upload/file',
                    success: function (data, result) {
                        var transformed = Formatter.apply(result);
                           scope.data = transformed;
                        scope.columns = _.map(result.header,
                            function(header){
                            return {"sTitle": header}
                        });
                        Widgets.addTable(null, $(element).parentsUntil('div.workspace:first'), scope);

                    }
                });
            }
        };
    }];
});