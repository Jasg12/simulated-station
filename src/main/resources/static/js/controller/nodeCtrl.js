'use strict';

/**
 *
 * @name nodeCtrl
 * @description
 * # nodeCtrl
 *
 * Controller for the Data Manager view
 */
var dashboardApp = angular.module('dashboardApp');

var controller = dashboardApp.controller('NodeCtrl', ['$scope', '$http', '$location', '$rootScope', '$routeParams',
    function ($scope, $http, $location, $rootScope, $routeParams) {
        /***** Variables *****/
        $scope.node = null;
        $scope.nodeId = $routeParams.nodeId;
        $scope.sensors = [];

        calculateViewPortSize();
        getNode();
        $( window ).resize(function () {
            calculateViewPortSize()
        });
        function calculateViewPortSize() {
            var height = window.innerHeight;
            $('.contacts-container').height(height -120);
        }

        function getNode() {
            var url = '/smart_node/' + $scope.nodeId;
            $http.get(url)
                .success(function(data){
                    console.log('Getting node from server', data);
                    $scope.node = data;
                })
                .error(function(error){
                    console.error(error);
                });
        }

        function getSensors(){
            var url = '';
            $http.get(url)
                .success(function(data){
                    console.log('Getting node from server', data);
                    $scope.node = data;
                })
                .error(function(error){
                    console.error(error);
                });
        }

        $scope.submit = function () {
            var url = '/smart_node/update/' + $scope.node.internalId + '/registered/' + $scope.node.registered;
            $http.put(url, JSON.stringify($scope.node))
                .success(function(data){
                    console.log('Node changes submitted');
                    $scope.node = data;
                })
                .error(function(error){
                    console.error(error);
                });
        }
    }
]);