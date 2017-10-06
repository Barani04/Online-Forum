/**
 * Query Detail Controller
 */

app.controller('QueAnsDetailController',function(QueAnsService,$scope,$location,$routeParams){
	var queid = $routeParams.questionid
	
	$scope.getQuery = QueAnsService.getQuery(queid).then(function(response){
		$scope.que = response.data
		console.log(response.data)
		console.log(response.status)
		isvoteQuestion(queid)
	},function(response){
		if(response.status=401){
			$scope.error = response.data
			console.log(response.status)
			$location.path('/login')
		}
	})
	
	function isvoteQuestion(queid) { 
		QueAnsService.isvoteQuestion(queid).then(function(response){
			console.log(response.data)
			if(response.data == ""){
				$scope.vq = false
			}
			else
				$scope.vq = true
		},function(response){
			if(response.status=401){
				$scope.error = response.data
				console.log(response.status)
				$location.path('/login')
			}
		})
	}
})