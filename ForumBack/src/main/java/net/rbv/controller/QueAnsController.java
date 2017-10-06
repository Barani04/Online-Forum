package net.rbv.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.rbv.dao.QuestionDao;
import net.rbv.dao.VoteQueDao;
import net.rbv.model.Question;
import net.rbv.model.VoteQuestion;
import net.rbv.service.Error;

@Controller
public class QueAnsController {
	
	@Autowired
	private QuestionDao questiondao;
	
	@Autowired
	private VoteQueDao votequedao;
	
	@RequestMapping(value="/submitquery",method=RequestMethod.POST)
	public ResponseEntity<?> submitQuery(@RequestBody Question quest,HttpSession session) {
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		quest.setPostedOn(new Date());
		quest.setVote(0);
		questiondao.submitQuery(quest);
		return new ResponseEntity<Question>(quest,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/allqueries",method=RequestMethod.GET)
	public ResponseEntity<?> getAllQueries(HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Question> queries = questiondao.getAllQuestions();
		return new ResponseEntity<List<Question>>(queries,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/viewquery/{queid}",method=RequestMethod.GET)
	public ResponseEntity<?> getQuery(@PathVariable int queid,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		Question question = questiondao.getQuestionById(queid);
		return new ResponseEntity<Question>(question,HttpStatus.OK);
	}
	
	@RequestMapping(value="/isvotedquery/{queid}",method=RequestMethod.GET)
	public ResponseEntity<?> isVotedQuery(@PathVariable int queid,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username = (String) session.getAttribute("username");
		VoteQuestion isvoted = votequedao.isVotedQuery(queid,username);
		return new ResponseEntity<VoteQuestion>(isvoted,HttpStatus.OK);
	}

}
