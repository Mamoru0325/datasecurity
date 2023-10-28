package th.ac.ku.kps.eng.cpe.datasecurity.service;

import th.ac.ku.kps.eng.cpe.datasecurity.model.Refreshtoken;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.ac.ku.kps.eng.cpe.datasecurity.repository.RefreshtokenRepository;

@Service
public class RefreshtokenService {
	@Value("${jwt.token.jwtRefreshExpirationMs}")
	private Long refreshTokenDurationMs;
	@Autowired
	private RefreshtokenRepository refreshtokenRepository;
	@Autowired
	private UserService userService;

	public Optional<Refreshtoken> findByToken(String token) {
		return refreshtokenRepository.findByToken(token);
	}

	public Refreshtoken createRefreshToken(int userId) {
		Refreshtoken refreshToken = new Refreshtoken();

		refreshToken.setUser(userService.findById(userId));
		Instant instant = Instant.now().plusMillis(refreshTokenDurationMs);
		refreshToken.setExpiryDate(Timestamp.from(instant));
		refreshToken.setToken(UUID.randomUUID().toString());

		refreshToken = refreshtokenRepository.save(refreshToken);
		return refreshToken;
	}

	public Refreshtoken verifyExpiration(Refreshtoken token) {
		if (token.getExpiryDate().toInstant().compareTo(Instant.now()) < 0) {
			refreshtokenRepository.delete(token);
			throw new RuntimeException(token.getToken() + "Refresh token was expired. Please make a new signin request");
		}

		return token;
	}

	@Transactional
	public int deleteByUserId(int userId) {
		return refreshtokenRepository.deleteByUser(userService.findById(userId));
	}

}
