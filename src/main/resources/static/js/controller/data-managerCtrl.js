'use strict';

/**
 *
 * @name datamanagerCtrl
 * @description
 * # datamanagerCtrl
 *
 * Controller for the Data Manager view
 */
var dashboardApp = angular.module('dashboardApp');

var controller = dashboardApp.controller('DataManagerDashboardCtrl', ['$scope', '$http', '$location', '$rootScope', '$routeParams',
    function ($scope, $http, $location, $rootScope, $routeParams) {
        /***** Variables *****/
        $scope.smartcluster = [];
        $scope.smartnode = [];
        $scope.smartsensor = [];
        $scope.content1 = [];
        $scope.content2 = [];
        $scope.content3 = [];
        $scope.from1 = '';
        $scope.to1 = '';
        $scope.from2 = '';
        $scope.to2 = '';
        $scope.from3 = '';
        $scope.to3 = '';
        $scope.sensorid = '';
        $scope.viewSelector = 'cluster';




        calculateViewPortSize();
        $( window ).resize(function () {
            calculateViewPortSize()
        });


        showTableStatClusterView();


        function calculateViewPortSize() {
            var height = window.innerHeight;
            $('.data-manager-dashboard-container').height(height -120);
        }

        $scope.viewChanged = function () {
            console.log("The view was changed to " + $scope.viewSelector);
            var t = $scope.viewSelector;
            t = t + '';
            //console.log(t);
            t = t.split(' ');
            t = t.pop();
            //console.log(t=='node');
            //console.log(t=='sensor');
            switch (t) {
                case 'smartcluster':
                    console.log('cluster');
                    $scope.smartcluster = true;
                    $scope.smartnode = false;
                    $scope.sensor = false;
                    showTableStatClusterView();

                    break;
                case 'smartnode':
                    console.log('node');

                    $scope.smartcluster = false;
                    $scope.smartnode = true;
                    $scope.sensor = false;
                    showTableStatNodeView();

                    break;
                case 'smartsensor':

                    $scope.smartcluster = false;
                    $scope.smartnode = false;
                    $scope.sensor = true;
                    showTableStatSensorView();
                    break;


                case 'cluster':
                    console.log('cluster');
                    //$scope.smartcluster = true;
                    //$scope.smartnode = false;
                    //$scope.sensor = false;
                    showTableStatClusterView();
                    break;
                case 'node':
                    console.log('node');
                    //$scope.smartcluster = false;
                    //$scope.smartnode = true;
                    //$scope.sensor = false;
                    showTableStatNodeView();
                    break;
                case 'sensor':
                    console.log('sensor');
                    //$scope.smartcluster = false;
                    //$scope.smartnode = false;
                    //$scope.sensor = true;
                    showTableStatSensorView();
                    break;
                case 'sensordatabysensorid' :
                    console.log('sensordatabysensorid');
                    showTableStatSensoridView();
            }

        };

        /***** Callbacks *****/
        $scope.search = function () {
            var from1 = new Date($scope.from1);
            var to1 = new Date($scope.to1);
            console.log("from:", $scope.from1);
            console.log("to:", $scope.to1);
            var url = '/sensor_data/sensor/data/by/cluster/from/' + from1.getTime() + '/to/' + to1.getTime();
            console.log("Prepare the call to url:" + url);

            var data = {clusterId:1};
            $http.post(url,JSON.stringify(data))
                .success(function(data){
                    console.log('Getting data from server', data);
                    $scope.smartcluster = data;
                })
                .error(function(error){
                    console.error(error);
                });
        };

        $scope.search1 = function () {
            var from2 = new Date($scope.from2);
            var to2 = new Date($scope.to2);
            var url = '/sensor_data/sensor/data/by/node/from/' + from2.getTime() + '/to/' + to2.getTime();
            console.log("Prepare the call to url:" + url);
            var data = {nodeId:1};
            $http.post(url,JSON.stringify(data))
                .success(function(data){
                    console.log('Getting data from server', data);
                    $scope.smartnode = data;
                })
                .error(function(error){
                    console.error(error);
                });
        };

        $scope.search2 = function () {
            var from3 = new Date($scope.from3);
            var to3 = new Date($scope.to3);
            var url = '/sensor_data/sensor/data/by/sensor/from/' + from3.getTime() + '/to/' + to3.getTime();
            console.log("Prepare the call to url:" + url);
            var data = {sensorId:1};
            $http.post(url,JSON.stringify(data))
                .success(function(data){
                    console.log('Getting data from server', data);
                    $scope.sensor = data;
                })
                .error(function(error){
                    console.error(error);
                });
        };

        $scope.search3 = function () {

            var url = '/sensor_data/sensor/data/by/sensor/ '+ $scope.sensorid;
            console.log("Prepare the call to url:" + url);
            var data = {sensorId:1};
            $http.post(url,JSON.stringify(data))
                .success(function(data){
                    console.log('Getting data from server', data);
                    $scope.sensorid = data;
                })
                .error(function(error){
                    console.error(error);
                });
        };
        $scope.search2 = function () {
            var from3 = new Date($scope.from3);
            var to3 = new Date($scope.to3);
            var url = '/sensor_data/sensor/data/by/sensor/from/' + from3.getTime() + '/to/' + to3.getTime();
            console.log("Prepare the call to url:" + url);
            var data = {sensorId:1};
            $http.post(url,JSON.stringify(data))
                .success(function(data){
                    console.log('Getting data from server', data);
                    $scope.sensor = data;
                })
                .error(function(error){
                    console.error(error);
                });
        };


        /*Internal functions*/

        function showTableStatClusterView(){
            $('.table-data-cluster-view').removeClass('disabled');
            $('.table-data-node-view').addClass('disabled');
            $('.table-data-sensor-view').addClass('disabled');
            $('.table-data-sensorid-view').addClass('disabled');
        }

        function showTableStatNodeView(){

            $('.table-data-node-view').removeClass('disabled');
            $('.table-data-cluster-view').addClass('disabled');
            $('.table-data-sensor-view').addClass('disabled');
            $('.table-data-sensorid-view').addClass('disabled');
        }

        function showTableStatSensorView(){

            $('.table-data-sensor-view').removeClass('disabled');
            $('.table-data-node-view').addClass('disabled');
            $('.table-data-cluster-view').addClass('disabled');
            $('.table-data-sensorid-view').addClass('disabled');

        }

        function showTableStatSensoridView(){
            $('.table-data-sensorid-view').removeClass('disabled');
            $('.table-data-sensor-view').addClass('disabled');
            $('.table-data-node-view').addClass('disabled');
            $('.table-data-cluster-view').addClass('disabled');

        }


    }
]);