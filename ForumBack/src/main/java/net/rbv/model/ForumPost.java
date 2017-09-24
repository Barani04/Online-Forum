package net.rbv.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class ForumPost {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int postId;
	
	@NotEmpty
	private String postQue;
	
	@ManyToOne
	@JoinColumn(name="postedBy")
	private User postedBy;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostQue() {
		return postQue;
	}

	public void setPostQue(String postQue) {
		this.postQue = postQue;
	}

	public User getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}
	
}
