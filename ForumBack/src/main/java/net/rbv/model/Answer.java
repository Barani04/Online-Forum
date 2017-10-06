package net.rbv.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;


@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int answerId;

	@Lob
	private String answer;

	@ManyToOne
	@JoinColumn(name = "questionId")
	private Question question;

	private String username;

	private String emailid;
	
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date answerdOn;
	
	private boolean verifiedAnswer;
	
	private int likes;

	public Date getAnswerdOn() {
		return answerdOn;
	}

	public void setAnswerdOn(Date answerdOn) {
		this.answerdOn = answerdOn;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
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

	public boolean isVerifiedAnswer() {
		return verifiedAnswer;
	}

	public void setVerifiedAnswer(boolean verifiedAnswer) {
		this.verifiedAnswer = verifiedAnswer;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	

}
