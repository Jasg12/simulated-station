'use strict';

/**
 *
 * @name sensorCtrl
 * @description
 * # sensorCtrl
 *
 * Controller for the Data Manager view
 */
var dashboardApp = angular.module('dashboardApp');

var controller = dashboardApp.controller('SensorCtrl', ['$scope', '$http', '$location', '$rootScope', '$routeParams',
    function ($scope, $http, $location, $rootScope, $routeParams) {
        /***** Variables *****/
        $scope.sensor = null;
        $scope.sensorId = $routeParams.sensorId;

        calculateViewPortSize();
        getSensor();

        $( window ).resize(function () {
            calculateViewPortSize()
        });
        function calculateViewPortSize() {
            var height = window.innerHeight;
            $('.contacts-container').height(height -120);
        }

        function getSensor() {
            var url = '/sensor/' + $scope.sensorId;
            $http.get(url)
                .success(function(data){
                    console.log('Getting sensor from server', data);
                    $scope.sensor = data;
                })
                .error(function(error){
                    console.error(error);
                });
        }

        $scope.submit = function () {
            var url = '/sensor/update/';
            $http.put(url, JSON.stringify($scope.sensor))
                .success(function(data){
                    console.log('Node changes submitted');
                    $scope.sensor = data;
                })
                .error(function(error){
                    console.error(error);
                });
        }
    }
]);