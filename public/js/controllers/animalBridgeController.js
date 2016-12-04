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

app.service("loginService", function() {
    var self = this;
    var login = {
      email : "",
      password : ""
    };

    self.getLogin = function (){
      return login;
    }

    self.setLogin = function (newLogin){
      login = newLogin;
      console.log("from service: email: " + login.email + " pwd: " + login.password);
    }
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

    .when('/allPosts', {
        templateUrl: 'viewPosts.html',
        controller: 'viewPostsController',
        controllerAs: 'MainCtrl'
    })

    .when('/emergency', {
        templateUrl: 'emergency.html',
        controller: 'emergencyController',
        controllerAs: 'MainCtrl'
    });

    $locationProvider.html5Mode(false);
}]);
app.config(['$httpProvider', function($httpProvider) {
    // $httpProvider.defaults.useXDomain = true;
    // $httpProvider.defaults.withCredentials = true;
    // delete $httpProvider.defaults.headers.common["X-Requested-With"];
    // $httpProvider.defaults.headers.common["Accept"] = "application/json";
    // $httpProvider.defaults.headers.common["Content-Type"] = "application/json";
}]);

app.controller('animalBridgeController', ['pageLayoutService', 'loginService', '$http', function(pageLayoutService, loginService, $http) {
    var self = this;
    self.name = "AnimalBridgeApp";
    self.headerTemplate = 'header.html';
    pageLayoutService.setShowHeader(true);
    pageLayoutService.setShowNavBar(true);
    self.showHeader = pageLayoutService.getShowHeader();
    self.showNav = pageLayoutService.getShowNavBar();

    self.signinSubmit = function (){
      console.log(self.login);
      loginService.setLogin(self.login);

      $http.post('http://localhost:8080/TeamMinions/rest/hello/testGet/', self.login)
          .then(function(response) {
            login = {};

          }, function(err){
            console.log("SERVER ERROR!!!");
          });
    };

}]);

app.controller('viewPostsController', ['pageLayoutService', '$http', function(pageLayoutService, $http) {
    var self = this;
    self.name = "AnimalBridgeApp";
    self.headerTemplate = 'header.html';
    pageLayoutService.setShowHeader(true);
    pageLayoutService.setShowNavBar(true);
    self.showHeader = pageLayoutService.getShowHeader();
    self.showNav = pageLayoutService.getShowNavBar();

    self.items = [];
    $http.get('http://localhost:8080/TeamMinions/rest/hello/testGet/')
    .then(
      function(response) {
        self.items = response.data;
    },
    function(errResponse) {
        console.error('Error while fetching notes');
    });

}]);

app.controller('aboutController', ['pageLayoutService', '$http', function(pageLayoutService, $http) {
    var self = this;
    self.name = "AnimalBridgeApp";
    self.headerTemplate = 'header.html';
    pageLayoutService.setShowHeader(true);
    pageLayoutService.setShowNavBar(true);
    self.showHeader = pageLayoutService.getShowHeader();
    self.showNav = pageLayoutService.getShowNavBar();

    self.lists = $(function() {

        $('.toggles .CatButton').click(function() {
            var get_id = this.id;
            var get_current = $('.posts .' + get_id);

            $('.post').not(get_current).hide(500);
            get_current.show(500);
        });


        $('#showall').click(function() {
            $('.post').show(500);
        });


    });


    self.us = $('select').on('change', function() {
        var optionVal = $(this).val();
        $('.grid-item').find('img').removeClass().addClass(optionVal);
    });



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

app.controller('emergencyController', ['pageLayoutService', '$scope', '$http', function(pageLayoutService, $scope, $http) {
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
        "category": "",
        "description": "",
        "imageData": "",
        "priority": "",
        "address": "",
        "date": "",
        "startingTime": "",
        "endingTime": "",
        "price": ""
    };

    self.selected = {
        "label": "",
        "priority": ""
    };

    pageLayoutService.setShowHeader(false);
    pageLayoutService.setShowNavBar(true);
    self.showHeader = pageLayoutService.getShowHeader();
    self.showNav = pageLayoutService.getShowNavBar();

    self.categories = [{
        label: 'Emergency',
        priority: 6
    }, {
        label: 'Lost',
        priority: 5
    }, {
        label: 'AdpotionOffer',
        priority: 4
    }, {
        label: 'AdpotionRequest',
        priority: 3
    }, {
        label: 'RecentNews',
        priority: 1
    }, {
        label: 'ShelterPromotion',
        priority: 2
    }];
    // self.selectedCategoryId = 2;
    // self.selectedCategory = this.categories[1];
    // $scope.$watch(
    //     "self.selected",
    //     function handleFooChange(newValue, oldValue) {
    //         console.log("self.post.category: ", newValue);
    //         //self.post.priority = newValue.priority;
    //     }
    // );
    $scope.$watch(angular.bind(this, function() {
        return self.selected;
    }), function(newValue) {
        console.log('selected changed to ' + newValue.label);
        console.log('selected changed to ' + newValue.priority);
        self.post.category = newValue.label;
        self.post.priority = newValue.priority;
    });

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

    self.dp1 = $('#datepicker1').datepicker().on('changeDate', function(e) {
        console.log('datepicker is ' + e.format([0]));
        self.post.date = e.format([0]);
    });
    self.tp1 = $('#timepicker1').timepicker().on('changeTime.timepicker', function(e) {
        console.log('Starting time is ' + e.time.value);
        self.post.startingTime = e.time.value;
    });
    self.tp2 = $('#timepicker2').timepicker().on('changeTime.timepicker', function(e) {
        console.log('Ending time is ' + e.time.value);
        self.post.endingTime = e.time.value;
    });

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
