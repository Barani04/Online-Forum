package net.rbv.ForumBack.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {
	
	private JavaMailSender mailSender;
	
	private static String from="EDUHUB-Forum";
	
	

}
