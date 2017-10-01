/**
 *Authorization Service 
 */

app.factory('AuthService',['$http',function($http){
	var authService={};
	
	var base_url ="http://localhost:8085/ForumBack"
	
	authService.registerUser = function(user){
		return $http.post(base_url+"/registeruser",user)
	}
	
	authService.validateUser=function(user){
		return $http.post(base_url+"/validateuser",user)
	}
	
	authService.logout=function(){
		return $http.get(base_url+"/logout")
	}
	
	authService.getUserAccWaitingForActivation = function(){
		return $http.get(base_url+"/getusers/"+'New')
	}
	
	return authService;
}])
