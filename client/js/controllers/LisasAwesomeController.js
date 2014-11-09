define([ 'jquery'],
    function ($) {
        return [
            '$timeout',
            '$scope',
            function ($timeout, $scope) {
                $scope.$on('say-something', function(e, args){
                    $scope.myThing = args;
                });


            }
        ];
    });
