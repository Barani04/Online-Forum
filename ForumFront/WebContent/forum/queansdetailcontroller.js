/**
 * Query Detail Controller
 */

app.controller('QueAnsDetailController',function(QueAnsService,$scope,$rootScope,$location,$routeParams,$route){
	var queid = $routeParams.questionid
	
	$scope.ans={}
	$scope.vote={}
	
	$scope.getQuery = QueAnsService.getQuery(queid).then(function(response){
		$scope.que = response.data
		console.log(response.data)
		console.log(response.status)
		listAllAnswers(queid)
		votedUser(queid)
	},function(response){
		if(response.status=401){
			$scope.error = response.data
			console.log(response.status)
			$location.path('/login')
		}
	})
	
	$scope.submitAnswer = function(){
		$scope.ans.username = $rootScope.currentUser.userName
		$scope.ans.emailid = $rootScope.currentUser.mailId
		QueAnsService.submitAnswer(queid,$scope.ans).then(function(response){
			$scope.ans.answer = ""
			console.log(response.data)
			console.log(response.status)
			listAllAnswers(queid)
		},function(response){
			if(response.status=401){
				$scope.error = response.data
				console.log(response.status)
				$location.path('/login')
			}
		})
	}
	
	function listAllAnswers(queid){
		QueAnsService.listAllAnswers(queid).then(function(response){
			$scope.answers = response.data
			$scope.anslen = response.data.length
			console.log(response.data.length)
			console.log(response.status)
		},function(response){
			if(response.status=401){
				$scope.error = response.data
				console.log(response.status)
				$location.path('/login')
			}
		})
	}
	
	$scope.setbestAnswer =	function(answerId){
		QueAnsService.setbestAnswer(answerId).then(function(response){
			console.log(response.status+"bestansset")
			listAllAnswers(queid)
		},function(response){
			if(response.status=401){
				$scope.error = response.data
				console.log(response.status)
				$location.path('/login')
			}
		})
	}
	
	$scope.voteQuestion = function(){
		$scope.vote.questionid = queid
		$scope.vote.username = $rootScope.currentUser.userName
		QueAnsService.voteQuestion($scope.vote,queid).then(function(response){
			console.log(response.status+"voted")
			console.log(response.status)
			$route.reload();
		},function(response){
			if(response.status=401){
				$scope.error = response.data
				console.log(response.status)
				$location.path('/login')
			}
		})
	}
	
	function votedUser(queid){
		QueAnsService.votedUser(queid).then(function(response){
			$scope.vote = response.data
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