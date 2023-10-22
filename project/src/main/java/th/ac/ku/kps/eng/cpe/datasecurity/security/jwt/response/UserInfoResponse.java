package th.ac.ku.kps.eng.cpe.datasecurity.security.jwt.response;

import java.util.List;

import org.springframework.http.ResponseCookie;

public class UserInfoResponse {
	private Long id;
	private String username;
	private String email;
	private String token;
	private List<String> roles;
	
	public UserInfoResponse() {
		super();
	}


	public UserInfoResponse(Long id, String username, String email, String token, List<String> roles) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.token = token;
		this.roles = roles;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}
	
	

}
