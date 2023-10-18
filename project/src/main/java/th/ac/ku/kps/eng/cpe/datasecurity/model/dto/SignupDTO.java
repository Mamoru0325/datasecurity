package th.ac.ku.kps.eng.cpe.datasecurity.model.dto;

import javax.validation.constraints.Email;

public class SignupDTO {
	@Email
	private String email;
	private String password;
	private String username;
	private String phoneNumber;
	
	public SignupDTO() {
		
	}

	public SignupDTO(@Email String email, String password, String username, String phoneNumber) {
		super();
		this.email = email;
		this.password = password;
		this.username = username;
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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

}
