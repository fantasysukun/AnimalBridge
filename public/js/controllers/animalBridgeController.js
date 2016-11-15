var app = angular.module('AnimalBridgeApp', ['ngRoute']);

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
        controller: 'aboutController'
    })

    // route for the contact page
    .when('/contact', {
        templateUrl: 'contact.html',
        controller: 'contactController'
    })

    .when('/emergency', {
        templateUrl: 'emergency.html',
        controller: 'emergencyController'
    });

    $locationProvider.html5Mode(false);
}]);

app.controller('animalBridgeController', function($scope, $http) {
    $scope.name = "AnimalBridgeApp";
    this.name = "Marco";
});

app.controller('aboutController', function($scope, $http) {
    $scope.name = "About Us";
    this.name = "The Team";
});

app.controller('contactController', function($scope, $http) {
    $scope.name = "Contact Us";
    this.name = "Email: admin@animalbridge.org";
});

app.controller('emergencyController', function($scope, $http) {
    $scope.name = "Emergency";
    this.name = "Email: admin@animalbridge.org";

    var map;
    var infowindow;

    initialize();
    // google.maps.event.addDomListener(window, 'load', initialize);

    function initialize() {
        var pyrmont = new google.maps.LatLng(37.773972, -122.431297);

        map = new google.maps.Map(document.getElementById('gmap_canvas'), {
            center: pyrmont,
            zoom: 8
        });

        console.log("trying to init gmap");

        var request = {
            location: pyrmont,
            radius: 1000,
            bounds: map.getBounds(),
            keyword: ['veterinarians']
        };
        infowindow = new google.maps.InfoWindow();
        var service = new google.maps.places.PlacesService(map);
        service.nearbySearch(request, callback);
    };

    function callback(results, status) {
        if (status == google.maps.places.PlacesServiceStatus.OK) {
            for (var i = 0; i < results.length; i++) {
                createMarker(results[i]);
            }
        }
    };

    function createMarker(place) {
        var placeLoc = place.geometry.location;
        var marker = new google.maps.Marker({
            map: map,
            position: place.geometry.location
        });

        google.maps.event.addListener(marker, 'click', function() {
            infowindow.setContent(place.name);
            infowindow.open(map, this);
        });
    };
});
