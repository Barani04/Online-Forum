package net.rbv.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.rbv.dao.QuestionDao;
import net.rbv.model.Question;
import net.rbv.service.Error;

@Controller
public class QuestionController {
	
	@Autowired
	private QuestionDao questiondao;
	
	@RequestMapping(value="/submitquery",method=RequestMethod.POST)
	public ResponseEntity<?> submitQuery(@RequestBody Question quest,HttpSession session) {
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		quest.setPostedOn(new Date());
		questiondao.submitQuery(quest);
		return new ResponseEntity<Question>(quest,HttpStatus.OK);
		
	}

}
