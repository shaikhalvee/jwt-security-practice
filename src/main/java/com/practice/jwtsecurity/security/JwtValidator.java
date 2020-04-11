package com.practice.jwtsecurity.security;

import com.practice.jwtsecurity.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtValidator {

	private static String secretKey = "SHAIKHALVEE";

	public JwtUser validate(String token) {

		JwtUser user = null;
		try {
			// io.jsonwebtoken's Claim catches the json web token / jwts.
			Claims claimedJwtBody = Jwts.parser()
					// set jwt key
					.setSigningKey(secretKey)
					// parse token to get username, id & role to send to provider
					.parseClaimsJws(token)
					.getBody();
			user = new JwtUser();
			user.setUsername(claimedJwtBody.getSubject());
			user.setId(Long.parseLong((String) claimedJwtBody.get("userId")));
			user.setRole((String) claimedJwtBody.get("role"));
		} catch (Exception e) {
			log.error("Error occurred {}", e.getMessage());
		}
		return user;
	}
}
