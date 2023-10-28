package th.ac.ku.kps.eng.cpe.datasecurity.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import th.ac.ku.kps.eng.cpe.datasecurity.model.User;

@Component
@PropertySource("classpath:application.properties")
public class TokenProvider {
	@Value("${jwt.token.validity}")
	public long TOKEN_VALIDITY;

	@Value("${jwt.token.jwtSecret}")
	public String SIGNING_KEY;

	@Value("${jwt.authorities.key}")
	public String AUTHORITIES_KEY;

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(Authentication authentication) {
		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		// System.out.println("authorities >> "+authorities);
		return Jwts.builder().setSubject(authentication.getName()).claim(AUTHORITIES_KEY, authorities)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS256, SIGNING_KEY).compact();
	}
	
	

	public Boolean validateToken(String token, User userDetails) {
		final String username = getUsernameFromToken(token);
		System.out.println(username);
		// final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

		final Claims claims = getAllClaimsFromToken(token);

		final Collection<? extends GrantedAuthority> authorities = Arrays
				.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		for (GrantedAuthority g : authorities) {
			if (g.getAuthority().equals("ROLE_Anonymous"))
				return !isTokenExpired(token);
		}

		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public UsernamePasswordAuthenticationToken getAuthenticationToken(final String token,
			final Authentication existingAuth, final User userDetails) {

		final JwtParser jwtParser = Jwts.parser().setSigningKey(SIGNING_KEY);

		final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

		final Claims claims = claimsJws.getBody();

		final Collection<? extends GrantedAuthority> authorities = Arrays
				.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		if(userDetails == null )
			return new UsernamePasswordAuthenticationToken(getUsernameFromToken(token), null, authorities);


		return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, authorities);
	}

}
