package th.ac.ku.kps.eng.cpe.datasecurity.model;
// Generated Oct 16, 2023, 11:42:57 AM by Hibernate Tools 5.6.3.Final

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import th.ac.ku.kps.eng.cpe.datasecurity.model.dto.SignupDTO;

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

	private Integer userId;
	private String email;
	@JsonIgnore
	private String password;
	private String username;
	private String phoneNumber;
	private String status;
	@JsonIgnore
	private List<Feedback> feedbacks = new ArrayList<Feedback>();
	@JsonIgnore
	private List<Role> roles = new ArrayList<Role>();
	@JsonIgnore
	private List<Scoreboard> scoreboards = new ArrayList<Scoreboard>();
	@JsonIgnore
	private List<Refreshtoken> refreshtokens = new ArrayList<Refreshtoken>();

	public User() {
	}

	public User(String email, String username, String phoneNumber) {
		this.email = email;
		this.username = username;
		this.phoneNumber = phoneNumber;
	}

	public User(String email, String password, String username, String phoneNumber, String status,
			List<Feedback> feedbacks, List<Role> roles, List<Scoreboard> scoreboards, List<Refreshtoken> refreshtokens) {
		this.email = email;
		this.password = password;
		this.username = username;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.feedbacks = feedbacks;
		this.roles = roles;
		this.scoreboards = scoreboards;
		this.refreshtokens = refreshtokens;
	}

	public void signup(SignupDTO user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.username = user.getUsername();
		this.phoneNumber = user.getPhoneNumber();
	}
	
	public void clone (User user) {
		this.email = user.getEmail();
		this.username = user.getUsername();
		this.phoneNumber = user.getPhoneNumber();
		this.status = user.getStatus();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Scoreboard> getScoreboards() {
		return scoreboards;
	}

	public void setScoreboards(List<Scoreboard> scoreboards) {
		this.scoreboards = scoreboards;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public List<Refreshtoken> getRefreshtokens() {
		return refreshtokens;
	}

	public void setRefreshtokens(List<Refreshtoken> refreshtokens) {
		this.refreshtokens = refreshtokens;
	}

}
