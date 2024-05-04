package com.springsecurityandangular.springsecurityandangular.config;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.springsecurityandangular.springsecurityandangular.dto.UserDto;

import jakarta.annotation.PostConstruct;

@Component
public class UserAuthProvider {

	@Value("${security.jwt.token.secret-key:secret-key}")
	private String secretKey;
	
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String createToken(UserDto user) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + 3_600_000);
		
		return JWT.create()
				  .withIssuer(user.getLogin())
				  .withIssuedAt(now)
				  .withExpiresAt(validity)
				  .withClaim("firstName", user.getFirstName())
				  .withClaim("lastName", user.getLastName())
				  .sign(Algorithm.HMAC256(secretKey));
	}
	
	public Authentication validateToken(String token) {
		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		JWTVerifier verifier = JWT.require(algorithm).build();
		
		DecodedJWT decoded = verifier.verify(token);
		
		UserDto user = new UserDto(decoded.getClaim("firstName").asString(),
								   decoded.getClaim("lastName").asString(),
								   decoded.getClaim("login").asString());
		
		return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
	}
}
