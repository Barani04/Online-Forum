/**
 * App.js File
 */

var app = angular.module("myApp",['ngRoute','ngCookies'])

app.config(function($routeProvider) {
	$routeProvider
	.when('/',{
		templateUrl:'views/home.html'
	})
	.when('/contact',{
		templateUrl:'views/contact.html'
	})
})
