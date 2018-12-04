'use strict';

/**
 *
 * @name sportBettingApp
 * @description
 * # sport betting
 *
 * Main module of the application.
 */
angular
    .module('dashboardApp', [
        //    'ngAnimate',
        //    'ngCookies',
        'ngResource',
        'ngRoute'
    ])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'view/home-page.html'
            })
            .when('/dashboard/cluster', {
                templateUrl: 'view/cluster.html',
                controller: 'ClusterCtrl'
            })
            .when('/dashboard/node/:nodeId', {
                templateUrl: 'view/node.html',
                controller: 'NodeCtrl'
            })
            .when('/dashboard/sensor/:sensorId', {
                templateUrl: 'view/sensor.html',
                controller: 'SensorCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    });