package th.ac.ku.kps.eng.cpe.datasecurity.model;
// Generated Nov 1, 2023, 1:21:31 PM by Hibernate Tools 5.6.3.Final

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import th.ac.ku.kps.eng.cpe.datasecurity.model.dto.SignupDTO;

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

	private Integer userId;
	private String username;
	@JsonIgnore
	private String password;
	private String name;
	private String phoneNumber;
	private String status;
	@JsonIgnore
	private List<Feedback> feedbacks = new ArrayList<>();
	@JsonIgnore
	private List<Role> roles = new ArrayList<>();
	@JsonIgnore
	private List<Scoreboard> scoreboards = new ArrayList<>();
	@JsonIgnore
	private List<Refreshtoken> refreshtokens = new ArrayList<>();

	public User() {
	}

	public User(String username, String password, String name, String phoneNumber, String status) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.status = status;
	}

	public User(String username, String password, String name, String phoneNumber, String status,
			List<Feedback> feedbacks, List<Role> roles, List<Scoreboard> scoreboards,
			List<Refreshtoken> refreshtokens) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.feedbacks = feedbacks;
		this.roles = roles;
		this.scoreboards = scoreboards;
		this.refreshtokens = refreshtokens;
	}
	
	public void signup (SignupDTO signupDTO) {
		this.username = signupDTO.getEmail();
		this.password = signupDTO.getPassword();
		this.phoneNumber = signupDTO.getPhoneNumber();
		this.name = signupDTO.getUsername();
	}
	
	public void clone (User user) {
		this.username = user.username;
		this.name = user.name;
		this.password = user.password;
		this.phoneNumber = user.phoneNumber;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public List<Refreshtoken> getRefreshtokens() {
		return refreshtokens;
	}

	public void setRefreshtokens(List<Refreshtoken> refreshtokens) {
		this.refreshtokens = refreshtokens;
	}

}
