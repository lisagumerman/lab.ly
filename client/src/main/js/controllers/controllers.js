define([
    'angular',
    'controllers/ui/TabController',
    'controllers/ui/WorkspaceController'
], function(angular,
            TabController,
            WorkspaceController
){


    var Controllers = angular.module('Lably.Controllers', []);

    Controllers.controller(
        'TabController',
        TabController
    );

    Controllers.controller(
        'WorkspaceController',
        WorkspaceController
    );
    return Controllers;
});
