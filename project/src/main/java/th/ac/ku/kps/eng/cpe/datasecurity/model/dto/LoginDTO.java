package th.ac.ku.kps.eng.cpe.datasecurity.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginDTO {
	@Email
	private String username;
	@NotBlank
	private String password;
	
	public LoginDTO() {
		super();
	}

	public LoginDTO(@Email String username, @NotBlank String password) {
		super();
		this.username = username;
		this.password = password;
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
	

}
