package net.rbv.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VoteDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int voteid;
	
	private String username;
	
	private int questionid;

	public int getVoteid() {
		return voteid;
	}

	public void setVoteid(int voteid) {
		this.voteid = voteid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getQuestionid() {
		return questionid;
	}

	public void setQuestionid(int questionid) {
		this.questionid = questionid;
	}
	
	
}
