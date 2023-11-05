package th.ac.ku.kps.eng.cpe.datasecurity.security.jwt.response;

import java.util.List;

import org.springframework.http.ResponseCookie;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtResponse {
	private String token;
	@JsonIgnore
	private String type = "Bearer";
	private String refreshToken;
	@JsonIgnore
	private int id;
	private String name;
	@JsonIgnore
	private String username;
	private List<String> roles;

	public JwtResponse(String accessToken, String refreshToken, int id, String name, String username,
			List<String> roles) {
		this.token = accessToken;
		this.setRefreshToken(refreshToken);
		this.id = id;
		this.name = name;
		this.username = username;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
