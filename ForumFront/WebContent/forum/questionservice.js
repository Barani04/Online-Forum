/**
 * Question Service
 */
app.factory('QuestionService',function($http){
	var questionService={}
	
	var base_url ="http://localhost:8085/ForumBack"
	
	questionService.submitQuery =function(quest){
		return $http.post(base_url+"/submitquery",quest)
	}

	return questionService;
})