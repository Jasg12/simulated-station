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
            .when('/dashboard/data-manager', {
                templateUrl: 'view/node.html',
                controller: 'DataManagerDashboardCtrl'
            })
            .when('/terms',{
                templateUrl: 'view/terms.html',
                controller: 'TermsCtrl'
            })
            .when('/contacts',{
                templateUrl: 'view/cluster.html',
                controller: 'ContactsCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    });