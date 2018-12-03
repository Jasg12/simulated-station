'use strict';

/**
 *
 * @name TermsCtrl
 * @description
 * # TermsCtrl
 *
 * Controller for the Terms view
 */
var dashboardApp = angular.module('dashboardApp');

dashboardApp.controller('TermsCtrl', ['$scope', '$http', '$location', '$rootScope', '$routeParams',
    function ($scope, $http, $location, $rootScope, $routeParams) {
        calculateViewPortSize();
        $( window ).resize(function () {
            calculateViewPortSize()
        });
        function calculateViewPortSize() {
            var height = window.innerHeight;
            $('.terms-container').height(height -120);
        }
    }
]);