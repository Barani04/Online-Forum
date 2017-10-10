/**
 * Question Controller
 */

app.controller("QueAnsController",function(QueAnsService,$rootScope,$scope,$location){
	$scope.quest={}
	
	 $scope.name = $rootScope.currentUser.userName
	 $scope.mail = $rootScope.currentUser.mailId
	 
	 $scope.submitQuery = function(){
		QueAnsService.submitQuery($scope.quest).then(function(response){
			console.log(response.data)
			console.log(response.status)
			$scope.quest.question =""
				listallqueries()
		},function(response){
			if(response.status=401){
				$scope.error = response.data
				console.log(response.status)
				$location.path('/login')
			}
		})
	}
	
	function listallqueries(){
	QueAnsService.listallqueries().then(function(response){
		$scope.queries = response.data
		console.log(response.status)
	},function(response){
		if(response.status=401){
			$scope.error = response.data
			console.log(response.status)
			$location.path('/login')
		}
	})
	}
	
	
	listallqueries()
})