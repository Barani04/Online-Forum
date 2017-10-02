package net.rbv.controller;

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

import net.rbv.dao.UserDao;
import net.rbv.model.User;
import net.rbv.service.EmailService;
import net.rbv.service.Error;

@Controller
public class UserController {

	@Autowired
	private UserDao userdao;
	
	@Autowired
	private EmailService emailService;
	
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
			user.setRole("USER");
			userdao.registerUser(user);
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}catch (Exception e) {
			Error error = new Error(1, "Unable to Register User...");
			System.out.println(e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/validateuser", method = RequestMethod.POST)
	public ResponseEntity<?> validateUser(@RequestBody User user,HttpSession session) {
		User validuser = userdao.login(user);
		System.out.println(user.getMailId() + "--" + user.getUserName() + "--" + user.getPassword() + "--"
				+ user.getFirstName() + "--" + user.getLastName());
		if (validuser== null) {
			Error error = new Error(4, "Invalid Username/Password");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		
		else if(validuser.isAcc_status()==false){
			Error error = new Error(8,"Your Account is not yet activated..!Wait for Activation Mail");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_ACCEPTABLE);
		}
		session.setAttribute("username", validuser.getUserName());
		return new ResponseEntity<User>(validuser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username = (String) session.getAttribute("username");
		System.out.println(username);
		User user = userdao.getUserByUserName(username);
		session.removeAttribute(username);
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/getusers/{activated}",method=RequestMethod.GET)
	public ResponseEntity<?> getUserReq(@PathVariable int activated,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<User> users=userdao.getUsers(activated);
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@RequestMapping(value="/activateuser/{username}",method=RequestMethod.POST)
	public ResponseEntity<?> acctivateAcc(@PathVariable("username") String username,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error = new Error(5, "Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		User user =userdao.getUserByUserName(username);
		user.setAcc_status(true);
		userdao.update(user);
		emailService.approvedUserNotify(user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
		
}
