'use strict';

/**
 *
 * @name UserDashboardCtrl
 * @description
 * # UserDashboardCtrl
 *
 * Controller for the User Dashboard view
 */
var dashboardApp = angular.module('dashboardApp');

dashboardApp.controller('UserDashboardCtrl', ['$scope', '$http', '$location', '$rootScope', '$routeParams',
    function ($scope, $http, $location, $rootScope, $routeParams) {
        /***** Variables *****/
        $scope.streetStat = [];
        $scope.streetMapViewStat = [];
        $scope.connectivityStat = [];
        $scope.zip = '';
        $scope.state = '';
        $scope.city = '';
        $scope.viewSelector = 'Statistics';
        $scope.map = null;
        $scope.cityList = [];
        $scope.stateCityMap = {CA: ["San Jose", "San Francisco"]};
        $scope.cluster = null;
        $scope.clusterList = [];
        $scope.node = null;
        $scope.nodeList = [];
        $scope.sensor = null;
        $scope.sensorList = [];
        $scope.fromDate = '';
        $scope.toDate = '';

        /***** Startup actions *****/
        calculateViewPortSize();
        $( window ).resize(function () {
            calculateViewPortSize()
        });
        showTableStatView();
        initGoogleMap();

        var options = {
            format: 'mm/dd/yyyy',
            todayHighlight: true,
            autoclose: true
        };
        $('#from-date').datepicker(options);
        $('#to-date').datepicker(options);



        /***** Callbacks *****/
        $scope.viewChanged = function () {
            console.log("The view was changed to " + $scope.viewSelector);
            switch ($scope.viewSelector) {
                case 'Statistics':
                    showTableStatView();
                    break;
                case 'Map View':
                    showMapStatView();
                    break;
                case 'Connectivity View':
                    showConnectivityStatView();
                    break;
                case 'Sensor Data View':
                    showSensorDataView();
                    break;
            }

        };
        $scope.stateChange = function () {
            console.log("State selector changed");
            $scope.streetStat = [];
            $scope.streetMapViewStat = [];
            $scope.connectivityStat = [];
            $scope.cityList = $scope.stateCityMap[$scope.state];
        };
        $scope.cityChange = function () {
            getClusters();
            var url = '/infrastructure/stat/' + $scope.state + '/' + $scope.city;
            console.log("Prepare the call to url:" + url);
            $http.get(url)
                .success(function(data){
                    console.log('Getting data from server', data);
                    $scope.streetStat = data;
                })
                .error(function(error){
                    console.error(error);
                });
        };
        $scope.clusterChange = function () {
            getNodes();
        };
        $scope.nodeChange = function () {
            getSensors();
        };
        $scope.sensorChange = function () {

        };

        $scope.showSensorData = function () {
            var from = new Date($scope.fromDate);
            var to  = new Date($scope.toDate);
            var url = '/sensor_data/sensor/data/by/sensor/from/' + from.getTime() + '/to/' + to.getTime();
            console.log("Prepare the call to url:" + url);
            var data = {
                sensorId: parseInt($scope.sensor)
            };
            $http.post(url, JSON.stringify(data))
                .success(function(data){
                    console.log('Getting sensor data from server', data);
                    drawTemperatureChart(data);
                })
                .error(function(error){
                    console.error(error);
                });
        };


        /***** Internal functions *****/
        function calculateViewPortSize() {
            var height = window.innerHeight;
            $('.user-dashboard-container').height(height -120);
        }

        function showTableStatView(){
            $('.table-data-view').removeClass('disabled');
            $('.map-data-view').addClass('disabled');
            $('.connectivity-data-view').addClass('disabled');
            $('.sensor-data-view').addClass('disabled');
        }

        function showMapStatView(){
            $('.map-data-view').removeClass('disabled');
            $('.table-data-view').addClass('disabled');
            $('.connectivity-data-view').addClass('disabled');
            $('.sensor-data-view').addClass('disabled');

            if($scope.streetMapViewStat.length === 0){
                getMapViewData();
            } else {
                addClustersToMap($scope.map, $scope.streetMapViewStat);
            }
        }

        function showConnectivityStatView(){
            $('.connectivity-data-view').removeClass('disabled');
            $('.table-data-view').addClass('disabled');
            $('.map-data-view').addClass('disabled');
            $('.sensor-data-view').addClass('disabled');

            if($scope.connectivityStat.length === 0){
                getConnectivityData();
            }
        }

        function showSensorDataView(){
            $('.sensor-data-view').removeClass('disabled');
            $('.table-data-view').addClass('disabled');
            $('.map-data-view').addClass('disabled');
            $('.connectivity-data-view').addClass('disabled');
        }

        function getConnectivityData(){
            var url = '/infrastructure/connectivity/cluster/' + $scope.state + '/' + $scope.city;
            console.log("Prepare the call to url:" + url);
            $http.get(url)
                .success(function(data){
                    console.log('Getting data from server for map view', data);
                    $scope.connectivityStat = data;
                })
                .error(function(error){
                    console.error(error);
                });
        }


        function getClusters() {
            var url = '/smart_cluster/clusters/' + $scope.state + '/' + $scope.city;
            console.log("Prepare the call to url:" + url);
            $http.get(url)
                .success(function(data){
                    console.log('Getting clusters from server', data);
                    $scope.clusterList = data;
                })
                .error(function(error){
                    console.error(error);
                });
        }

        function getNodes() {
            var clusterId = $('#cluster').find(":selected").val();
            $scope.cluster = clusterId;
            var url = '/nodes/' + clusterId;
            console.log("Prepare the call to url:" + url);
            $http.get(url)
                .success(function(data){
                    console.log('Getting nodes from server', data);
                    $scope.nodeList = data;
                })
                .error(function(error){
                    console.error(error);
                });
        }

        function getSensors() {
            var nodeId = $('#node').find(":selected").val();
            $scope.node = nodeId;
            var url = '/sensors/' + nodeId;
            console.log("Prepare the call to url:" + url);
            $http.get(url)
                .success(function(data){
                    console.log('Getting nodes from server', data);
                    $scope.sensorList = data;
                })
                .error(function(error){
                    console.error(error);
                });
        }


        function getMapViewData(){
            var url = '/infrastructure/' + $scope.state + '/' + $scope.city;
            console.log("Prepare the call to url:" + url);
            $http.get(url)
                .success(function(data){
                    console.log('Getting data from server for map view', data);
                    $scope.streetMapViewStat = data;
                    addClustersToMap($scope.map, $scope.streetMapViewStat);
                })
                .error(function(error){
                    console.error(error);
                });
        }


        function addClustersToMap(map, clusters){
            console.log("Add clusters into map", clusters);
            for(var i=0;i<clusters.length;i++){
                var cluster = clusters[i];
                var clusterLocation = cluster.location;
                addOverlaytoMap(clusterLocation.longitude, clusterLocation.latitude, 'image/cluster-icon.png', map);
                for(var j=0;j<cluster.nodes.length;j++){
                    var node = clusters[i].nodes[j];
                    var nodeLocation = node.location;
                    addOverlaytoMap(nodeLocation.longitude, nodeLocation.latitude, 'image/node-icon.png', map);
                    var path = [
                        new google.maps.LatLng(clusterLocation.longitude + 0.0002, clusterLocation.latitude + 0.0002),
                        new google.maps.LatLng(nodeLocation.longitude + 0.0002, nodeLocation.latitude + 0.0002)
                    ];
                    drawPolyLine(path, map);

                    for (var t=0;t<node.sensors.length;t++){
                        var sensor = node.sensors[t];
                        var sensorLocation = sensor.location;
                        addOverlaytoMap(sensorLocation.longitude, sensorLocation.latitude, 'image/sensor-icon.png', map);
                        var path = [
                            new google.maps.LatLng(nodeLocation.longitude + 0.0002, nodeLocation.latitude + 0.0002),
                            new google.maps.LatLng(sensorLocation.longitude + 0.0002, sensorLocation.latitude + 0.0002)
                        ];
                        drawPolyLine(path, map);
                    }
                }
            }
        }

        function addOverlaytoMap(lat, lng, img, map){
            var bounds = new google.maps.LatLngBounds(
                new google.maps.LatLng(lat, lng),
                new google.maps.LatLng(lat + 0.0004, lng + 0.0004));
            var overlay = new USGSOverlay(bounds, img, map);
        }

        function drawPolyLine(path, map){
            var line = new google.maps.Polyline({
                path: path,
                geodesic: true,
                strokeColor: "#8c625e",
                strokeOpacity: 1.0,
                strokeWeight: 2,
                map: map
            });
        }

        function drawTemperatureChart(rowData){
            var margin = {top: 20, right: 20, bottom: 70, left: 40},
                width = 600 - margin.left - margin.right,
                height = 300 - margin.top - margin.bottom;

            // Parse the date / time
            var	parseDate = d3.time.format("%Y-%m-%d").parse;

            var x = d3.scale.ordinal().rangeRoundBands([0, width], .05);

            var y = d3.scale.linear().range([height, 0]);

            var xAxis = d3.svg.axis()
                .scale(x)
                .orient("bottom")
                .tickFormat(d3.time.format("%m/%d/%y"));

            var yAxis = d3.svg.axis()
                .scale(y)
                .orient("left")
                .ticks(10);

            var svg = d3.select("#chart-container").append("svg")
                .attr("width", width + margin.left + margin.right)
                .attr("height", height + margin.top + margin.bottom)
                .append("g")
                .attr("transform",
                    "translate(" + margin.left + "," + margin.top + ")");

         //   d3.csv("bar-data.csv", function(error, data) {
            //    var data = [{date: '2013-01', value: 53}, {date: '2013-02', value: 165}, {date: '2013-03', value: 269}, {date: '2013-04', value: 344}]
            var data = [];
                rowData.content.forEach(function(d) {
                    var date = new Date(d.timestamp);
                    console.log("date:", date);
                    data.push({date: new Date(d.timestamp), value: d.value.temperature});
                });

                x.domain(data.map(function(d) { return d.date; }));
                y.domain([0, d3.max(data, function(d) { return d.value; })]);

                svg.append("g")
                    .attr("class", "x axis")
                    .attr("transform", "translate(0," + height + ")")
                    .call(xAxis)
                    .selectAll("text")
                    .style("text-anchor", "end")
                    .attr("dx", "-.8em")
                    .attr("dy", "-.55em")
                    .attr("transform", "rotate(-90)" );

                svg.append("g")
                    .attr("class", "y axis")
                    .call(yAxis)
                    .append("text")
                    .attr("transform", "rotate(-90)")
                    .attr("y", 6)
                    .attr("dy", ".71em")
                    .style("text-anchor", "end")
                    .text("Temperature");

                svg.selectAll("bar")
                    .data(data)
                    .enter().append("rect")
                    .style("fill", "steelblue")
                    .attr("x", function(d) { return x(d.date); })
                    .attr("width", x.rangeBand())
                    .attr("y", function(d) { return y(d.value); })
                    .attr("height", function(d) { return height - y(d.value); });

        //    });
        }






        /********************* INIT Google Map *******************/

        function initGoogleMap(){
            $scope.map = new google.maps.Map(document.getElementById('map'), {
                center: {lat: 37.335694, lng: -121.902710},
                zoom: 16
            });
        }

        USGSOverlay.prototype = new google.maps.OverlayView();

        /** @constructor */
        function USGSOverlay(bounds, image, map) {

            // Initialize all properties.
            this.bounds_ = bounds;
            this.image_ = image;
            this.map_ = map;

            // Define a property to hold the image's div. We'll
            // actually create this div upon receipt of the onAdd()
            // method so we'll leave it null for now.
            this.div_ = null;

            // Explicitly call setMap on this overlay.
            this.setMap(map);
        }
        /**
         * onAdd is called when the map's panes are ready and the overlay has been
         * added to the map.
         */
        USGSOverlay.prototype.onAdd = function() {

            var div = document.createElement('div');
            div.style.borderStyle = 'none';
            div.style.borderWidth = '0px';
            div.style.position = 'absolute';
            div.style.zIndex = '1000';

            // Create the img element and attach it to the div.
            var img = document.createElement('img');
            img.src = this.image_;
            img.style.width = '100%';
            img.style.height = '100%';
            img.style.position = 'absolute';
            div.appendChild(img);

            this.div_ = div;

            // Add the element to the "overlayLayer" pane.
            var panes = this.getPanes();
            panes.overlayLayer.appendChild(div);
            panes.overlayLayer.style['zIndex'] = 1001;
          //  panes.mapPane.appendChild(div);
        };

        USGSOverlay.prototype.draw = function() {

            // We use the south-west and north-east
            // coordinates of the overlay to peg it to the correct position and size.
            // To do this, we need to retrieve the projection from the overlay.
            var overlayProjection = this.getProjection();

            // Retrieve the south-west and north-east coordinates of this overlay
            // in LatLngs and convert them to pixel coordinates.
            // We'll use these coordinates to resize the div.
            var sw = overlayProjection.fromLatLngToDivPixel(this.bounds_.getSouthWest());
            var ne = overlayProjection.fromLatLngToDivPixel(this.bounds_.getNorthEast());

            // Resize the image's div to fit the indicated dimensions.
            var div = this.div_;
            div.style.left = sw.x + 'px';
            div.style.top = ne.y + 'px';
            div.style.width = (ne.x - sw.x) + 'px';
            div.style.height = (sw.y - ne.y) + 'px';
        };

        USGSOverlay.prototype.onRemove = function() {
            this.div_.parentNode.removeChild(this.div_);
            this.div_ = null;
        };
    }
]);