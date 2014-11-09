define([
    'angular',
    'controllers/Main',
    'controllers/LisasAwesomeController'
], function(
    Angular,
    Main,
    LisasController
    ) {
    var Controllers = Angular.module("Lably.Controllers", []);
    Controllers.controller('LablyMain', Main);
    Controllers.controller('LisasAwesomeController', LisasController);
    return Controllers;
});
