var app = angular.module('AnimalBridgeApp', ['ngRoute']);

app.config(function($routeProvider, $locationProvider) {
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
    });

    // $locationProvider.html5Mode(true);
});

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
