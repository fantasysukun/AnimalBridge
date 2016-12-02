var app = angular.module('AnimalBridgeApp', ['ngRoute', ]);

app.service("pageLayoutService", function() {
    var self = this;
    var shouldShowHeader = true;
    var shouldShowNavBar = true;

    self.getShowHeader = function() {
        return shouldShowHeader;
    };
    self.getShowNavBar = function() {
        return shouldShowNavBar;
    };

    self.setShowHeader = function(newVal) {
        shouldShowHeader = newVal;
    };

    self.setShowNavBar = function(newVal) {
        shouldShowNavBar = newVal;
    };
});

jQuery(document).ready(function() {
    $('.launch-modal').on('click', function(e) {
        e.preventDefault();
        $('#' + $(this).data('modal-id')).modal();
    });
});


app.config(["$routeProvider", "$locationProvider", function($routeProvider, $locationProvider) {
    $routeProvider
    //route for the homepage
        .when('/', {
            templateUrl: 'home.html',
            controller: 'animalBridgeController'
        })
        // //route for the homepage
        //     .when('/#/', {
        //     templateUrl: 'home.html',
        //     controller: 'animalBridgeController'
        // })


    //route for about page
    .when('/about', {
        templateUrl: 'about.html',
        controller: 'aboutController',
        controllerAs: 'MainCtrl'
    })

    // route for the contact page
    .when('/contact', {
        templateUrl: 'contact.html',
        controller: 'contactController',
        controllerAs: 'MainCtrl'
    })

    .when('/newpost', {
        templateUrl: 'newPost.html',
        controller: 'newPostController',
        controllerAs: 'MainCtrl'
    })

    .when('/emergency', {
        templateUrl: 'emergency.html',
        controller: 'emergencyController',
        controllerAs: 'MainCtrl'
    });

    $locationProvider.html5Mode(false);
}]);

app.controller('animalBridgeController', ['pageLayoutService', '$http', function(pageLayoutService, $http) {
    var self = this;
    self.name = "AnimalBridgeApp";
    self.headerTemplate = 'header.html';
    pageLayoutService.setShowHeader(true);
    pageLayoutService.setShowNavBar(true);
    self.showHeader = pageLayoutService.getShowHeader();
    self.showNav = pageLayoutService.getShowNavBar();
}]);

app.controller('aboutController', ['pageLayoutService', '$http', function(pageLayoutService, $http) {
    var self = this;
    self.name = "AnimalBridgeApp";
    self.headerTemplate = 'header.html';
    pageLayoutService.setShowHeader(true);
    pageLayoutService.setShowNavBar(true);
    self.showHeader = pageLayoutService.getShowHeader();
    self.showNav = pageLayoutService.getShowNavBar();
}]);

app.controller('contactController', ['pageLayoutService', '$http', function(pageLayoutService, $http) {
    var self = this;
    self.name = "AnimalBridgeApp";
    self.headerTemplate = 'header.html';

    pageLayoutService.setShowHeader(true);
    pageLayoutService.setShowNavBar(true);
    self.showHeader = pageLayoutService.getShowHeader();
    self.showNav = pageLayoutService.getShowNavBar();
}]);

app.controller('emergencyController', ['pageLayoutService', '$scope','$http', function(pageLayoutService, $scope, $http) {
    var self = this;
    self.name = "AnimalBridgeApp";
    self.headerTemplate = 'header.html';

    pageLayoutService.setShowHeader(false);
    pageLayoutService.setShowNavBar(true);
    self.showHeader = pageLayoutService.getShowHeader();
    self.showNav = pageLayoutService.getShowNavBar();

    // 1. Google Map //
    var cities = [{
        city: 'Location 1',
        desc: 'Test',
        lat: 52.238983,
        long: -0.888509
    }, {
        city: 'Location 2',
        desc: 'Test',
        lat: 52.238168,
        long: -52.238168
    }, {
        city: 'Location 3',
        desc: 'Test',
        lat: 52.242452,
        long: -0.889882
    }, {
        city: 'Location 4',
        desc: 'Test',
        lat: 52.247234,
        long: -0.893567
    }, {
        city: 'Location 5',
        desc: 'Test',
        lat: 52.241874,
        long: -0.883568
    }];

    // Map Settings //
    $scope.initialise = function() {
        var myLatlng = new google.maps.LatLng(37.3000, -120.4833);
        var mapOptions = {
            center: myLatlng,
            zoom: 16,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById("map"), mapOptions);
        // Geo Location /
        navigator.geolocation.getCurrentPosition(function(pos) {
            map.setCenter(new google.maps.LatLng(pos.coords.latitude, pos.coords.longitude));
            var myLocation = new google.maps.Marker({
                position: new google.maps.LatLng(pos.coords.latitude, pos.coords.longitude),
                map: map,
                animation: google.maps.Animation.DROP,
                title: "My Location"
            });
        });
        $scope.map = map;
        // Additional Markers //
        $scope.markers = [];
        var infoWindow = new google.maps.InfoWindow();
        var createMarker = function(info) {
            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(info.lat, info.long),
                map: $scope.map,
                animation: google.maps.Animation.DROP,
                title: info.city
            });
            marker.content = '<div class="infoWindowContent">' + info.desc + '</div>';
            google.maps.event.addListener(marker, 'click', function() {
                infoWindow.setContent('<h2>' + marker.title + '</h2>' + marker.content);
                infoWindow.open($scope.map, marker);
            });
            $scope.markers.push(marker);
        }
        for (i = 0; i < cities.length; i++) {
            createMarker(cities[i]);
        }

    };
    google.maps.event.addDomListener(document.getElementById("map"), 'load', $scope.initialise());
}]);

app.controller('newPostController', ['pageLayoutService', '$scope', '$http', function(pageLayoutService, $scope, $http) {
    var self = this;
    self.name = "AnimalBridgeApp";
    self.headerTemplate = 'header.html';

    self.post = {
      "title": "",
      "email": "",
      "password": "",
      "category": null,
      "description": "",
      "imageData": ""
    };

    pageLayoutService.setShowHeader(false);
    pageLayoutService.setShowNavBar(true);
    self.showHeader = pageLayoutService.getShowHeader();
    self.showNav = pageLayoutService.getShowNavBar();

    self.categories = [{
        label: 'Emergency',
        id: 1
    }, {
        label: 'Information',
        id: 2
    }, {
        label: 'Product',
        id: 3
    }];
    self.selectedCategoryId = 2;
    self.selectedCategory = this.categories[1];

    self.submit = function() {
        console.log('User clicked submit with ', self.post);
    };

    self.addImageFile = function() {
        var file = document.getElementById('file').files[0],
        readFile = new FileReader();
        readFile.onloadend = function(e) {
          console.log("loaded image");
          // self.imageData = e.target.result;
          self.post.imageData = e.target.result;
        }
        readFile.readAsBinaryString(file);
    };
}]);

app.directive('googleplace', function() {
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, model) {
            var options = {
                types: [],
                componentRestrictions: {}
            };
            scope.gPlace = new google.maps.places.Autocomplete(element[0], options);

            google.maps.event.addListener(scope.gPlace, 'place_changed', function() {
                scope.$apply(function() {
                    model.$setViewValue(element.val());
                });
            });
        }
    };
});
//myApp.factory('myService', function() {});

function MyCtrl($scope) {
    $scope.gPlace;
}
