var app = angular.module("myApp", ["ngRoute"]);
app.config(function($routeProvider) {
    $routeProvider
    .when("/search/:param1", {
        templateUrl : "search.html",
        controller : "searchController"
    })
    .when("/login", {
        templateUrl : "login.html",
        controller : "loginController"
    }).when("/add", {
        templateUrl : "add.html",
        controller : "addController"
    })
    .when("/register", {
        templateUrl : "register.html",
        controller : "registerController"
    });
});
app.controller("loginController", function ($scope,$route, $routeParams) {
    $scope.msg = "User Login Page";

});
app.controller("registerController", function ($scope,$route, $routeParams) {
    $scope.msg = "register page";
});
app.controller("searchController", function ($scope,$route, $routeParams,$http) {
 
   $scope.q=$routeParams.param1;
  
        var ur="http://localhost/App/rest/UserService/count?search="+$scope.q;
 
      $http({method: 'GET', url: ur}).
        then(function(response) {
          $scope.status = response.status;
          $scope.data = response.data;
        }, function(response) {
          $scope.data = response.data || 'Request failed1';
          $scope.status = response.status;
      });
  
  var ur2="http://localhost/App/rest/UserService/users?search="+$scope.q;
  $http({method: 'GET', url: ur2}).
        then(function(response) {
          $scope.status2 = response.status;
          $scope.data2 = response.data;
        }, function(response) {
          $scope.data2 = response.data || 'Request failed2';
          $scope.status2 = response.status;
      });

 
 

});
app.controller('myCtrl', function($scope, $location) {
    $scope.searchData=function(search){
       $location.path('/search/'+search);
    };
});
app.controller('addController', function($scope, $location, $route, $routeParams,$http) {
  $scope.path="";
    $scope.addPath=function(){
      console.log($scope.path);
        var ur2="http://localhost/App/rest/UserService/add?path="+$scope.path;
$http({method: 'GET', url: ur2}).
        then(function(response) {
          $scope.status3 = response.status;
          $scope.data3 = response.data;
        }, function(response) {
          $scope.data3 = response.data || 'Request failed2';
          $scope.status3 = response.status;
      });
$scope.path="";
    };
});
