define([ 'jquery'],
    function ($) {
        return [
            '$timeout',
            '$scope',
            '$rootScope',
            function ($timeout, $scope, $rootScope) {
                $scope.whatever = "Hello";
                $scope.sayHello = function() {
                    $rootScope.$broadcast('say-something', 'I like Ike :)');
                }


            }
        ];
    });
