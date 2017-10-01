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
		templateUrl:'authorize/login.html'
	})
	.when('/register',{
		templateUrl:'authorize/signup.html'
	})
})
