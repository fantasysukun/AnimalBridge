var app = angular.module('AnimalBridgeApp', ['ngRoute',]);

app.service("pageLayoutService", function(){
  var self = this;
  var shouldShowHeader = true;
  var shouldShowNavBar = true;

  self.getShowHeader = function () {
    return shouldShowHeader;
  };
  self.getShowNavBar = function (){
    return shouldShowNavBar;
  };

  self.setShowHeader = function (newVal){
    shouldShowHeader = newVal;
  };

  self.setShowNavBar = function (newVal) {
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

app.controller('animalBridgeController', ['pageLayoutService','$http', function(pageLayoutService,$http) {
    var self = this;
    self.name = "AnimalBridgeApp";
    self.headerTemplate = 'header.html';
    pageLayoutService.setShowHeader(true);
    pageLayoutService.setShowNavBar(true);
    self.showHeader = pageLayoutService.getShowHeader();
    self.showNav = pageLayoutService.getShowNavBar();
}]);

app.controller('aboutController', ['pageLayoutService','$http', function(pageLayoutService,$http) {
    var self = this;
    self.name = "AnimalBridgeApp";
    self.headerTemplate = 'header.html';
    pageLayoutService.setShowHeader(true);
    pageLayoutService.setShowNavBar(true);
    self.showHeader = pageLayoutService.getShowHeader();
    self.showNav = pageLayoutService.getShowNavBar();
}]);

app.controller('contactController', ['pageLayoutService','$http', function(pageLayoutService,$http) {
    var self = this;
    self.name = "AnimalBridgeApp";
    self.headerTemplate = 'header.html';

    pageLayoutService.setShowHeader(true);
    pageLayoutService.setShowNavBar(true);
    self.showHeader = pageLayoutService.getShowHeader();
    self.showNav = pageLayoutService.getShowNavBar();
}]);

app.controller('emergencyController', ['pageLayoutService','$http', function(pageLayoutService,$http) {
    var self = this;
    self.name = "AnimalBridgeApp";
    self.headerTemplate = 'header.html';

    pageLayoutService.setShowHeader(false);
    pageLayoutService.setShowNavBar(true);
    self.showHeader = pageLayoutService.getShowHeader();
    self.showNav = pageLayoutService.getShowNavBar();
}]);

app.controller('newPostController', ['pageLayoutService','$http', function(pageLayoutService,$http) {
    var self = this;
    self.name = "AnimalBridgeApp";
    self.headerTemplate = 'header.html';

    pageLayoutService.setShowHeader(false);
    pageLayoutService.setShowNavBar(true);
    self.showHeader = pageLayoutService.getShowHeader();
    self.showNav = pageLayoutService.getShowNavBar();

    self.categories = [
      {label: 'Emergency', id: 1},
      {label: 'Information', id: 2},
      {label: 'Product', id: 3}
    ];
    self.selectedCategoryId = 2;
    self.selectedCategory = this.categories[1];
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
