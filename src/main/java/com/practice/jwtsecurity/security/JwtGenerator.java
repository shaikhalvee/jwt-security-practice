package com.practice.jwtsecurity.security;

import com.practice.jwtsecurity.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {

	private static final String secretKey = "SHAIKHALVEE";

	/**
	 * @param jwtUser provides userInfo
	 * @return json web token by given userInfo
	 */
	public String generate(JwtUser jwtUser) {
		// creating a claim and set values into it
		Claims claims = Jwts.claims()
				.setSubject(jwtUser.getUsername());
		claims.put("userId", jwtUser.getId());
		claims.put("role", jwtUser.getRole());
		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}
}
