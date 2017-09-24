package net.rbv.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="ForumReply")
public class ForumPostReply {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int replyId;
	
	@NotEmpty
	private String postReply;
	
	@ManyToOne
	@JoinColumn(name="forumpost")
	private ForumPost forum;
	
	@ManyToOne
	@JoinColumn(name="repliedBy")
	private User repliedBy;

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public String getPostReply() {
		return postReply;
	}

	public void setPostReply(String postReply) {
		this.postReply = postReply;
	}

	public ForumPost getForum() {
		return forum;
	}

	public void setForum(ForumPost forum) {
		this.forum = forum;
	}

	public User getRepliedBy() {
		return repliedBy;
	}

	public void setRepliedBy(User repliedBy) {
		this.repliedBy = repliedBy;
	}
	
	
}
