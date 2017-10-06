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
		return $http.get(base_url+"/allqueries")
	}
	
	queAnsService.getQuery = function(queid){
		return $http.get(base_url+"/viewquery/"+queid)
	}
	
	queAnsService.isvoteQuestion = function(queid){
		return $http.get(base_url+"/isvotedquery/"+queid)
	}

	return queAnsService;
})