/**
 * Question Service
 */
app.factory('QueAnsService',function($http){
	var queAnsService={}
	
	var base_url ="http://localhost:8085/ForumBack"
	
	queAnsService.submitQuery =function(quest){
		return $http.post(base_url+"/submitquery",quest)
	}
	
	queAnsService.listallqueries=function(){
		return $http.get(base_url+"/allqueries/"+1)
	}
	
	queAnsService.changeQueryStatus = function(questionId){
		return $http.post(base_url+"/approvepost/"+questionId)
	}
	
	queAnsService.getForumPostsWaitingForApproval = function(){
		return $http.get(base_url+"/allqueries/"+0)
	}
	
	queAnsService.getQuery = function(queid){
		return $http.get(base_url+"/viewquery/"+queid)
	}
	
	queAnsService.submitAnswer = function(queid,ans){
		return $http.post(base_url+"/submitans/"+queid,ans)
	}
	
	queAnsService.listAllAnswers = function(queid){
		return $http.get(base_url+"/listans/"+queid)
	}

	queAnsService.setbestAnswer = function(answerId){
		return $http.post(base_url+"/setbestans",answerId)
	}
	
	queAnsService.voteQuestion = function(vote,queid){
		return $http.post(base_url+"/votequery/"+queid,vote)
	}
	
	queAnsService.votedUser = function(queid){
		return $http.get(base_url+"/voteduser/"+queid)
	}

	return queAnsService;
})