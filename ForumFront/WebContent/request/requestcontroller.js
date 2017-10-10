/**
 * Request Controller
 */
app.controller('RequestController',function(AuthService,QueAnsService,$scope,$rootScope,$location,$route,$cookieStore){
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
	
	$scope.changeAccStatus=function(name){
		AuthService.changeAccStatus(name).then(function(response){
			$scope.changeAccStatus=response.data;
			$scope.succ="User Account Activated...!"
			$route.reload();
		},function(response){
			$scope.error=response.data
			$location.path('/home')
		})
	}
	
	QueAnsService.getForumPostsWaitingForApproval().then(function(response){
		if(response.data.length==0){
			$rootScope.mess="No New Forum Posts for Approval...!"
		}
		$rootScope.len=response.data.length;
		$cookieStore.put("len",response.data.length)
		$scope.postsForApproval = response.data;
	},function(response){
		if(response.status==401){
			$scope.error=response.data
			$location.path('/login')
		}
	})
	
	$scope.changeQueryStatus=function(questionId){
		QueAnsService.changeQueryStatus(questionId).then(function(response){
			$scope.approveStatus = response.data;
			$route.reload();
		},function(response){
			$scope.error=response.data
			$location.path('/home')
		})
		}
	
})