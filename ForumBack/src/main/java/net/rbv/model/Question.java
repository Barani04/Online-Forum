package net.rbv.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;


@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int questionId;
	
	@Lob
	private String question;
	
	private String username;
	
	private String emailid;

	@Temporal(javax.persistence.TemporalType.DATE)
	private Date postedOn;
	
	private int noOfAns;
	
	private boolean approvestatus;
	
	private int votes;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getPostQue() {
		return question;
	}

	public void setPostQue(String postQue) {
		this.question = postQue;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;	
	}

	public int getNoOfAns() {
		return noOfAns;
	}

	public void setNoOfAns(int noOfAns) {
		this.noOfAns = noOfAns;
	}

	public boolean isApprovestatus() {
		return approvestatus;
	}

	public void setApprovestatus(boolean approvestatus) {
		this.approvestatus = approvestatus;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

}
	
