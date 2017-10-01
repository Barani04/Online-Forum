package net.rbv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.rbv.dao.UserDao;
import net.rbv.model.User;
import net.rbv.service.Error;

@Controller
public class UserController {

	@Autowired
	private UserDao userdao;
	
	@RequestMapping(value="/registeruser", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user){
		try{
			User duplicateUser = userdao.validateUserName(user.getUserName());
			if(duplicateUser !=null){
				Error error = new Error(2, "Username already Exists...");
				return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
			}
			User duplicateMailId = userdao.validateMailId(user.getMailId());
			if(duplicateMailId !=null){
				Error error = new Error(2, "Mail Id already Registered...");
				return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
			}
			userdao.registerUser(user);
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}catch (Exception e) {
			Error error = new Error(1, "Unable to Register User...");
			System.out.println(e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
}
