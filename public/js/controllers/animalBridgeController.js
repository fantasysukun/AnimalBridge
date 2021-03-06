var app = angular.module('AnimalBridgeApp', ["ngRoute"]);

app.service("pageLayoutService", function() {
    var self = this;
    var shouldShowHeader = true;
    var shouldShowNavBar = true;
    var showSignUp = true;

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

    self.getShowSignUp = function() {
        return showSignUp;
    };

    self.setShowSignUp = function(newVal) {
        showSignUp = newVal;
    };

});

// app.service("loginService", function() {
//     var self = this;
//     var login = {
//         email: "",
//         password: ""
//     };
//
//     self.getLogin = function() {
//         return login;
//     }
//
//     self.setLogin = function(newLogin) {
//         login = newLogin;
//         console.log("from service: email: " + login.email + " pwd: " + login.password);
//     }
// });

app.factory("signinService", ["$rootScope", "$http", "$location", function($rootScope, $http, $location) {
    var login = {
        email: "",
        password: ""
    };

    var userInfo = {
        signinStatus: false,
        userID: -1,
        userName: ""
    }

    $rootScope.userInfo = userInfo;

    return {
        signinVerify: function() {
            return userInfo.signinStatus;
        },

        setSignin: function(l) {
            login = l;
        },

        getSignin: function() {
            return login;
        },

        getUserInfo: function() {
            return userInfo;
        },

        logout: function() {
            login = {
                email: "",
                password: ""
            };

            userInfo = {
                signinStatus: false,
                userID: -1,
                userName: ""
            };

            $rootScope.userInfo = userInfo;
        },

        signinNow: function() {
            $http.post('./rest/hello/testPostLogin/', login)
                .then(function(response) {
                    userInfo.signinStatus = response.data.user;
                    if (userInfo.signinStatus == "true") {
                        userInfo.userName = response.data.userName;
                        userInfo.userID = response.data.id;
                        // $(window).location = "./login.html";
                        $rootScope.userInfo = userInfo;
                        $location.path('/login');
                    } else {
                        userInfo.userName = "Not Found";
                    }
                    console.log("from service::: signedin T/F: " + userInfo.signinStatus);
                    console.log("user : " + userInfo.userName);
                    // scroll window to top
                    var move = function() {
                        $(window).scrollTop(0);
                    };

                    return true;
                }, function(err) {
                    console.log(err);
                    return false;
                });
        }
    }
}]);

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

    .when('/login', {
        templateUrl: 'login.html',
        controller: 'loginController',
        controllerAs: 'MainCtrl'
    })

    .when('/forget', {
        templateUrl: 'forget.html',
        controller: 'forgetController',
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

app.controller('animalBridgeController', ['pageLayoutService', 'signinService', '$http', '$location', '$scope',function(pageLayoutService, signinService, $http, $location, $scope) {
    var self = this;
    self.name = "AnimalBridgeApp";
    self.headerTemplate = 'header.html';
    pageLayoutService.setShowHeader(true);
    pageLayoutService.setShowNavBar(true);
    pageLayoutService.setShowSignUp(true);
    self.showHeader = pageLayoutService.getShowHeader();
    self.showNav = pageLayoutService.getShowNavBar();
    self.showSignUp = pageLayoutService.getShowSignUp();

    var userInfo = {
        signinStatus: false,
        userID: -1,
        userName: ""
    }

    self.userVerified = signinService.getUserInfo().signinStatus;

    $scope.$watch(
      'self.userVerified',
      function(newValue, oldValue){
        if (newValue != oldValue) {
            console.log("watch:::" + newValue);
            $scope.$apply();
        }
      }
    )
    // console.log("animalBridgeController:::" + signinService.getUserInfo().userName + " verified::" + signinService.getUserInfo().signinStatus + "::self::" + self.userVerified);
    // self.userVerification = {
    //     "user": ''
    // };
    self.signinSubmit = function() {
        console.log(self.login);
        // loginService.setLogin(self.login);
        //
        // $http.post('./rest/hello/testPostLogin/', self.login)
        //     .then(function(response) {
        //         self.userVerification = response.data;
        //         if(response.data.user == "true"){
        //           self.userVerified = true;
        //           // $(window).location = "./login.html";
        //           $location.path('/login');
        //         }
        //         else{
        //           self.userVerified = false;
        //         }
        //         console.log("T/F: " + self.userVerified);
        //         // scroll window to top
        //         var move = function() {
        //             $(window).scrollTop(0);
        //         };
        //         login = {};
        //     }, function(err) {
        //         console.log("SERVER ERROR!!!");
        //     });
        console.log("calling signinService");
        signinService.setSignin(self.login);
        signinService.signinNow();

    };
    self.logout = function() {
        signinService.logout();
    }

}]);

app.controller('loginController', ['pageLayoutService', 'signinService', '$http', function(pageLayoutService, signinService, $http) {
    var self = this;
    self.name = "AnimalBridgeApp";
    self.headerTemplate = 'header.html';
    pageLayoutService.setShowHeader(false);
    pageLayoutService.setShowNavBar(true);
    pageLayoutService.setShowSignUp(false);
    self.showHeader = pageLayoutService.getShowHeader();
    self.showNav = pageLayoutService.getShowNavBar();
    self.showSignUp = pageLayoutService.getShowSignUp();

    self.user = signinService.getUserInfo();
    self.userVerified = self.user.signinStatus;
    console.log("loginController:::"+self.userVerified);

    self.logout = function() {
        signinService.logout();
    }
}]);

app.controller('forgetController', ['pageLayoutService', 'signinService', '$http', function(pageLayoutService, signinService, $http) {
    var self = this;
    self.name = "AnimalBridgeApp";
    self.headerTemplate = 'header.html';
    pageLayoutService.setShowHeader(false);
    pageLayoutService.setShowNavBar(true);
    pageLayoutService.setShowSignUp(false);
    self.showHeader = pageLayoutService.getShowHeader();
    self.showNav = pageLayoutService.getShowNavBar();
    self.showSignUp = pageLayoutService.getShowSignUp();

    self.user = signinService.getUserInfo();
    self.userVerified = self.user.signinStatus;
    console.log("forgetController:::"+self.userVerified);

    self.userVerified = signinService.getUserInfo().signinStatus;
    console.log("forgetController:::" + signinService.getUserInfo().userName + " verified::" + signinService.getUserInfo().signinStatus + "::self::" + self.userVerified);
    self.signinSubmit = function() {
        console.log(self.login);
        console.log("calling signinService");
        signinService.setSignin(self.login);
        signinService.signinNow();

    };

    self.logout = function() {
        signinService.logout();
    }

    self.forget = {
        email: "",
        password: "ForgetPassword"
    };

    self.submit = function() {
        $http.post('./rest/hello/forget/', self.forget)
        .then(function(response){
          console.log("hehehe:::"+response);
          console.log("forget::::data.user:"+response.data.user+"---"+response.user);
          if (response.data.user == "true") {
            self.recovered = response.data.password;
          }
          else{
            self.recovered = "NO USER FOUND";
          }
        }, function(err){
          console.log("forgetController:::SERVER ERROR");
        })};
}]);

app.controller('viewPostsController', ['pageLayoutService', 'signinService', '$http', function(pageLayoutService, signinService, $http) {
    var self = this;
    self.name = "AnimalBridgeApp";
    self.headerTemplate = 'header.html';
    pageLayoutService.setShowHeader(true);
    pageLayoutService.setShowNavBar(true);
    pageLayoutService.setShowSignUp(true);
    self.showHeader = pageLayoutService.getShowHeader();
    self.showNav = pageLayoutService.getShowNavBar();
    self.showSignUp = pageLayoutService.getShowSignUp();
    self.items = [];

    self.userVerified = signinService.getUserInfo().signinStatus;
    console.log("HOME:::" + signinService.getUserInfo().userName + " verified::" + signinService.getUserInfo().signinStatus + "::self::" + self.userVerified);
    self.signinSubmit = function() {
        console.log(self.login);
        console.log("calling signinService");
        signinService.setSignin(self.login);
        signinService.signinNow();

    };

    self.logout = function() {
            signinService.logout();
        }
        // $http.get('http://localhost:8080/TeamMinions/rest/hello/kjkjk34343')
    console.log("VIEWPOSTCONTROLLER:::::");
    $http.get('./rest/hello/testGetPost/')
        .then(
            function(response) {
                self.items = response.data;
                console.log(self.items);
            },
            function(errResponse) {
                console.error('Error while fetching notes');
            });



}]);

app.controller('aboutController', ['pageLayoutService', '$http', 'signinService', function(pageLayoutService, $http, signinService) {
    var self = this;
    self.name = "AnimalBridgeApp";
    self.headerTemplate = 'header.html';
    pageLayoutService.setShowHeader(true);
    pageLayoutService.setShowNavBar(true);
    pageLayoutService.setShowSignUp(true);
    self.showSignUp = pageLayoutService.getShowSignUp();
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

    self.userVerified = signinService.getUserInfo().signinStatus;
    console.log("HOME:::" + signinService.getUserInfo().userName + " verified::" + signinService.getUserInfo().signinStatus + "::self::" + self.userVerified);
    self.signinSubmit = function() {
        console.log(self.login);
        console.log("calling signinService");
        signinService.setSignin(self.login);
        signinService.signinNow();

    };

    self.logout = function() {
        signinService.logout();
    }

}]);





app.controller('contactController', ['pageLayoutService', 'signinService', '$http', function(pageLayoutService, signinService, $http) {
    var self = this;
    self.name = "AnimalBridgeApp";
    self.headerTemplate = 'header.html';

    pageLayoutService.setShowHeader(true);
    pageLayoutService.setShowNavBar(true);
    pageLayoutService.setShowSignUp(true);
    self.showSignUp = pageLayoutService.getShowSignUp();
    self.showHeader = pageLayoutService.getShowHeader();
    self.showNav = pageLayoutService.getShowNavBar();

    self.userVerified = signinService.getUserInfo().signinStatus;
    console.log("HOME:::" + signinService.getUserInfo().userName + " verified::" + signinService.getUserInfo().signinStatus + "::self::" + self.userVerified);
    self.signinSubmit = function() {
        console.log(self.login);
        console.log("calling signinService");
        signinService.setSignin(self.login);
        signinService.signinNow();

    };
    self.logout = function() {
        signinService.logout();
    }
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

app.controller('newPostController', ['pageLayoutService', 'signinService','$scope', '$http', function(pageLayoutService, signinService, $scope, $http) {
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
        "price": "",
        "ownerID": "",
        "ownerName": ""
    };

    self.selected = {
        "label": "",
        "priority": ""
    };

    pageLayoutService.setShowHeader(false);
    pageLayoutService.setShowNavBar(true);
    pageLayoutService.setShowSignUp(false);
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
    var dp1 = $('#datepicker1').datepicker().on('changeDate', function(e) {
        console.log('datepicker is ' + e.format([0]));
        self.post.date = e.format([0]);
    });
    var tp1 = $('#timepicker1').timepicker().on('changeTime.timepicker', function(e) {
        console.log('Starting time is ' + e.time.value);
        self.post.startingTime = e.time.value;
    });
    var tp2 = $('#timepicker2').timepicker().on('changeTime.timepicker', function(e) {
        console.log('Ending time is ' + e.time.value);
        self.post.endingTime = e.time.value;
    });

    $scope.initializeTimeicker1 = function() {
        $('#timepicker1').timepicker().on('changeTime.timepicker', function(e) {
            console.log('Starting time is ' + e.time.value);
            self.post.startingTime = e.time.value;
        });
    };

    $scope.initializeTimeicker2 = function() {
        $('#timepicker2').timepicker().on('changeTime.timepicker', function(e) {
            console.log('Ending time is ' + e.time.value);
            self.post.endingTime = e.time.value;
        });
    };

    $scope.$watch(angular.bind(this, function() {
        return self.selected;
    }), function(newValue) {
        console.log('selected changed to ' + newValue.label);
        console.log('selected changed to ' + newValue.priority);
        self.post.category = newValue.label;
        self.post.priority = newValue.priority;
        tp1.timepicker();
        // $('#timepicker1').timepicker().on('changeTime.timepicker', function(e) {
        //     console.log('Starting time is ' + e.time.value);
        //     self.post.startingTime = e.time.value;
        // });
    });

    self.submit = function() {
        console.log('User clicked submit with ', self.post);
        $http.post('./rest/hello/InsertPost', self.post)
            .then(function(response) {
                console.log(response);
            }, function(err) {
                console.log(err);
            });
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

    self.userVerified = signinService.getUserInfo().signinStatus;
    console.log("HOME:::" + signinService.getUserInfo().userName + " verified::" + signinService.getUserInfo().signinStatus + "::self::" + self.userVerified);
    self.signinSubmit = function() {
        console.log(self.login);
        console.log("calling signinService");
        signinService.setSignin(self.login);
        signinService.signinNow();

    };

    self.logout = function() {
        signinService.logout();
    }


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

app.controller("MainController", ['pageLayoutService', '$http', '$scope', '$location', function(pageLayoutService, $http, $scope, $location) {
    var self = this;
    $scope.title = "This is a message";
    $scope.body = "Welcome Modal";
    $scope.showSignUpButton = pageLayoutService.getShowSignUp();
    $(".pulse-button").on("click", function() {

        $(".modal-container").css('z-index', 3000);

        $scope.signup = {
            "name": "",
            "email": "",
            "password": ""
        };
        $scope.re = {
            "repassword": ""
        };
        $scope.signUpSubmit = function() {
            console.log("clickedxxxxx");
            if ($scope.signup.password !== $scope.re.repassword) {
                console.log("yyy");
            } else {
                console.log($scope.signup);

                $http.post('./rest/hello/InsertUser/', $scope.signup)
                    .then(function(response) {
                        // self.userVerification = response.data;
                        // if(response.data.user == "true"){
                        //   self.userVerified = true;
                        //   // $(window).location = "./login.html";
                        //   $location.path('/login');
                        // }
                        // else{
                        //   self.userVerified = false;
                        // }
                        // console.log("T/F: " + self.userVerified);
                        // // scroll window to top
                        // var move = function() {
                        //     $(window).scrollTop(0);
                        // };
                        // login = {};
                        console.log(response);
                        $location.path('/login');


                    }, function(err) {
                        console.log("SERVER ERROR!!!");
                    });
            }
        };

    });

}]);
app.directive("modalWindow", function() {
    return {
        restrict: "E",
        template: "<div class = 'signup'><div class='btncontainer'><button ng-show='showSignUpButton' ng-click='open()' class='pulse-button btn-info'>Sign Up</button><div ng-hide='hidden' class='trans-layer'></div><div class='modal-container' ng-class='{modalactive: !hidden}' ng-transclude></div></div> <div>",
        scope: true,
        transclude: true,
        controller: function($scope) {
            $scope.hidden = true;
            $scope.open = function() {
                $scope.hidden = false;
            };
        },
        link: function(scope, ele, attrs) {
            $(ele).find('.trans-layer').on('click', function(event) {
                scope.hidden = true;
                // scope.$apply();
            })
        }
    }
});
app.directive("signinHeader", ['signinService', function(signinService) {
    return {
        restrict: "E",
        templateUrl: "header.html",
        scope: true,
        link: function($scope, $element, $attrs) {
            $scope.hidden = signinService.getUserInfo().signinStatus;
            // $scope.$apply();
        }
    }
}]);

app.directive("signupHeader", ['signinService', function(signinService) {
    return {
        restrict: "E",
        templateUrl: "signUp.html",
        scope: true,
        link: function($scope, $element, $attrs) {
            $scope.hidden = signinService.getUserInfo().signinStatus;
            // $scope.$apply();
        }
    }
}]);
