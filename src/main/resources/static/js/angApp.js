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
            .when('/dashboard/nodes', {
                templateUrl: 'view/nodes.html',
                controller: 'NodesCtrl'
            })
            .when('/dashboard/data-manager', {
                templateUrl: 'view/data-managerView.html',
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