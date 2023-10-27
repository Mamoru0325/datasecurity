package th.ac.ku.kps.eng.cpe.datasecurity.model.dto;

import th.ac.ku.kps.eng.cpe.datasecurity.model.User;

public class TokenRefreshResponse {
	private String accessToken;
	private String refreshToken;
	private String tokenType = "Bearer";
	private User user;

	public TokenRefreshResponse(User user, String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String token) {
		this.accessToken = token;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
