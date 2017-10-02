/**
 * Authorization Controller
 */

app.controller('AuthController',function(AuthService,$scope,$rootScope,$location,$cookieStore){
	$scope.user = {}
	
	$scope.registerUser=function(){
		AuthService.registerUser($scope.user).then(function(response) {
			$rootScope.notify='Registered Successfully...Wait for your Account Activation Mail'
			console.log(response.data)
			$location.path('/login')	
		},function(response){
			console.log(response.status)
			$scope.error=response.data
			console.log(response.data)
			$location.path('/register')
		})
	}
	
	$scope.validateUser=function(){
		AuthService.validateUser($scope.user).then(function(response) {
			console.log(response.data)
			$rootScope.currentUser=response.data
			$rootScope.message=undefined
			$cookieStore.put("currentUser",response.data)
			$location.path('/')
			
			AuthService.getUserAccWaitingForActivation().then(function(response){
				$rootScope.ul=response.data.length;
				$cookieStore.put("ul",response.data.length)
			},function(response){
				$scope.error=response.data
				$location.path('/login')
		})
		
		},function(response){
			if(response.status=401){
				$scope.error = response.data
				console.log(response.status)
				$location.path('/login')
			}
			if(response.status=406){
				$scope.error = response.data
				console.log(response.status)
				$location.path('/login')
			}
		})
	}	
})