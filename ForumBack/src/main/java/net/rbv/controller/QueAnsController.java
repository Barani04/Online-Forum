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

import net.rbv.dao.AnswerDao;
import net.rbv.dao.QuestionDao;
import net.rbv.dao.VoteDetailDao;
import net.rbv.model.Answer;
import net.rbv.model.Question;
import net.rbv.model.VoteDetails;
import net.rbv.service.Error;

@Controller
public class QueAnsController {
	
	@Autowired
	private QuestionDao questiondao;
	
	@Autowired
	private AnswerDao answerdao;
	
	@Autowired
	private VoteDetailDao votedetaildao;
	
	@RequestMapping(value="/submitquery",method=RequestMethod.POST)
	public ResponseEntity<?> submitQuery(@RequestBody Question quest,HttpSession session) {
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		quest.setPostedOn(new Date());
		quest.setNoOfAns(0);
		quest.setApprovestatus(false);
		quest.setVotes(0);
		questiondao.submitQuery(quest);
		return new ResponseEntity<Question>(quest,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/allqueries/{status}",method=RequestMethod.GET)
	public ResponseEntity<?> getAllQueries(@PathVariable boolean status){
		List<Question> queries = questiondao.getAllQuestions(status);
		return new ResponseEntity<List<Question>>(queries,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/approvepost/{queid}",method=RequestMethod.POST)
	public ResponseEntity<?> approveQuery(@PathVariable int queid,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		Question query = questiondao.getQuestionById(queid);
		query.setApprovestatus(true);
		questiondao.updateQuestion(query);
		return new ResponseEntity<Question>(query,HttpStatus.OK);
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
	
	@RequestMapping(value="/submitans/{queid}",method=RequestMethod.POST)
	public ResponseEntity<?> submitAnswer(@PathVariable int queid,@RequestBody Answer ans,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		Question ques = questiondao.getQuestionById(queid);
		ques.setNoOfAns(ques.getNoOfAns()+1);
		ans.setQuestion(ques);
		ans.setAnswerdOn(new Date());
		System.out.println(ans.toString());
		questiondao.updateQuestion(ques);
		answerdao.submitAnswer(ans);
		
		return new ResponseEntity<Answer>(ans,HttpStatus.OK);
	}
	
	@RequestMapping(value="/listans/{queid}",method=RequestMethod.GET)
	public ResponseEntity<?> listAllAnswers(@PathVariable int queid,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Answer> answers = answerdao.getAllAnswers(queid);
		
		return new ResponseEntity<List<Answer>>(answers,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/setbestans",method=RequestMethod.POST)
	public ResponseEntity<?> setBestAnswer(@RequestBody int answerId,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		Answer ans = answerdao.getAnswerById(answerId);
		ans.setVerifiedAnswer(true);
		answerdao.updateAnswer(ans);
		return new ResponseEntity<Answer>(ans,HttpStatus.OK);
	}
	
	@RequestMapping(value="/votequery/{queid}",method=RequestMethod.POST)
	public ResponseEntity<?> voteQuestion(@PathVariable int queid,@RequestBody VoteDetails vote,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		String username = (String) session.getAttribute("username");
		Question query = questiondao.getQuestionById(queid);
		query.setVotes(query.getVotes()+1);
		votedetaildao.saveVote(vote);
		questiondao.updateQuestion(query);
		return new ResponseEntity<Question>(query,HttpStatus.OK);
	}
	
	@RequestMapping(value="/voteduser/{queid}",method=RequestMethod.GET)
	public ResponseEntity<?> votedUser(@PathVariable int queid,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		String username = (String) session.getAttribute("username");
		VoteDetails vote = votedetaildao.getVotedUser(queid,username);
		return new ResponseEntity<VoteDetails>(vote,HttpStatus.OK);
	}
	
}
