'use strict';

/**
 *
 * @name ClusterCtrl
 * @description
 * # ClusterCtrl
 *
 * Controller for the Contacts view
 */
var dashboardApp = angular.module('dashboardApp');

dashboardApp.controller('ClusterCtrl', ['$scope', '$http', '$location', '$rootScope', '$routeParams',
    function ($scope, $http, $location, $rootScope, $routeParams) {

        $scope.cluster = null;
        $scope.registered = null;
        $scope.nodes = [];
        $scope.newNode = {
            name: null,
            model: null,
            make: null,
            location: {
                longitude: null,
                latitude: null,
                state: null,
                city: null,
                street: null,
                zipCode: null
            }
        };

        calculateViewPortSize();
        $( window ).resize(function () {
            calculateViewPortSize()
        });
        function calculateViewPortSize() {
            var height = window.innerHeight;
            $('.contacts-container').height(height -120);
        }
        gettingCluster();

        function gettingCluster(){
            var url = '/smart_cluster/';
            $http.get(url)
                .success(function(data){
                    console.log('Getting cluster from server', data);
                    $scope.cluster = data;
                })
                .error(function(error){
                    console.error(error);
                });
            var registeredUrl = '/smart_cluster/is/registered';
            $http.get(registeredUrl)
                .success(function(data){
                    console.log('Getting cluster registered status', data);
                    $scope.registered = data;
                })
                .error(function(error){
                    console.error(error);
                });
            getNodes();
        }

        function getNodes(){
            var registeredUrl = '/smart_node/nodes';
            $http.get(registeredUrl)
                .success(function(data){
                    console.log('Getting nodes', data);
                    $scope.nodes = data;
                })
                .error(function(error){
                    console.error(error);
                });
        }

        $scope.submit = function () {
            console.log('Submit cluster changes');
            var url = '/smart_cluster/update';
            $http.put(url, JSON.stringify($scope.cluster))
                .success(function(data){
                   console.log('Cluster changes submitted');
                    gettingCluster();
                })
                .error(function(error){
                    console.error(error);
                });
        };

        $scope.addNode = function () {
            console.log('Add new Node');
            var url = '/smart_node/create';
            $http.post(url, JSON.stringify($scope.newNode))
                .success(function(data){
                    console.log('Cluster changes submitted');
                    gettingCluster();
                })
                .error(function(error){
                    console.error(error);
                });
        }
    }
]);