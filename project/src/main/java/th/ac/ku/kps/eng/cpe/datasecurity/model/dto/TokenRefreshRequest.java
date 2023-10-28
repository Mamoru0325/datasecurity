package th.ac.ku.kps.eng.cpe.datasecurity.model.dto;

import javax.validation.constraints.NotBlank;

public class TokenRefreshRequest {
	@NotBlank
	private String refreshToken;

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
