package net.rbv.ForumBack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	
	@NotEmpty
	private String userNane;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String firstName;
	
	private String lastName;
	
	@Column(unique=true,nullable=false)
	private String mailId;
	
	
	private String role;
	
	
	private String acc_status;


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserNane() {
		return userNane;
	}


	public void setUserNane(String userNane) {
		this.userNane = userNane;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getMailId() {
		return mailId;
	}


	public void setMailId(String mailId) {
		this.mailId = mailId;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getAcc_status() {
		return acc_status;
	}


	public void setAcc_status(String acc_status) {
		this.acc_status = acc_status;
	}
	
	
}
