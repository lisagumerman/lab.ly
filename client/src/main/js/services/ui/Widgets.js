define(['jquery'], function($){

    return ['$compile', function($compile) {
        return {
            hello: function() {
                alert("Hello");
            },

            addTable:function(data, workspace, scope) {
                var el = $compile("<widget><datatable></datatable></widget>")(scope);
                workspace.append(el);
            }
        }
    }];



});