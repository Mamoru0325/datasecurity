package th.ac.ku.kps.eng.cpe.datasecurity.security.jwt.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private static final String type = "Bearer";
	private String userName;
	private List<String> roles;

	public JwtResponse() {

	}

	public JwtResponse(String token, String userName, List<String> roles) {
		super();
		this.token = token;
		this.userName = userName;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public static String getType() {
		return type;
	}

}
