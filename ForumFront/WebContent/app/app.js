/**
 * App.js File
 */

var app = angular.module("myApp",['ngRoute','ngCookies'])

app.config(function($routeProvider) {
	$routeProvider
	.when('/',{
		templateUrl:'views/home.html'
	})
	.when('/login',{
		templateUrl:'authorize/login.html',
		controller:'AuthController'
	})
	.when('/register',{
		templateUrl:'authorize/signup.html',
		controller:'AuthController'
	})
	.when('/userreq',{
		templateUrl:'request/userreq.html',
		controller:'RequestController'
	})
	
	.when('/viewquery/:questionid',{
		templateUrl:'forum/viewquery.html',
		controller:'QueAnsDetailController'
	})
})

app.run(function(AuthService,$rootScope,$cookieStore,$location) {
	
	if($rootScope.currentUser==undefined){
		$rootScope.currentUser = $cookieStore.get("currentUser")
	}
	
	$rootScope.logout=function(){
		AuthService.logout().then(function(response) {
		$rootScope.message='Logged Out Successfully'
			$('#notify').fadeIn(100).delay(2000).fadeOut(200);
			delete $rootScope.currentUser
			$cookieStore.remove("currentUser")
			$location.path('/login')
	},function(response){
		$rootScope.error=response.data
		$location.path('/login')
	})
}
})