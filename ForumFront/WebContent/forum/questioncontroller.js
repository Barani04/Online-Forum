/**
 * Question Controller
 */

app.controller("QuestionController",function(QuestionService,$rootScope,$scope,$location){
	$scope.quest={}
	
	 $scope.name = $rootScope.currentUser.userName
	 $scope.mail = $rootScope.currentUser.mailId
	 
	 $scope.submitQuery = function(){
		QuestionService.submitQuery($scope.quest).then(function(response){
			console.log(response.data)
			console.log(response.status)
		},function(response){
			if(response.status=401){
				$scope.error = response.data
				console.log(response.status)
				$location.path('/login')
			}
		})
	}
})