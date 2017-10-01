/**
 * Request Controller
 */
app.controller('RequestController',function(AuthService,$scope,$rootScope,$location,$route,$cookieStore){
	$scope.user={}
	
	AuthService.getUserAccWaitingForActivation().then(function(response){
		if(response.data.length==0){
			$scope.mes="No New User Requests for Approval...!"
		}
		$rootScope.ul=response.data.length;
		$cookieStore.put("ul",response.data.length)
		$scope.userRequests=response.data;
	},function(response){
			$scope.error=response.data
			$location.path('/login')
	})
})