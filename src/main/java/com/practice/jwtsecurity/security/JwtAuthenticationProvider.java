package com.practice.jwtsecurity.security;

import com.practice.jwtsecurity.model.JwtAuthenticationToken;
import com.practice.jwtsecurity.model.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private JwtValidator jwtValidator;

	// For additional authentication check, like in
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
	                                              UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

	}

	@Override
	protected UserDetails retrieveUser(String username,
	                                   UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// convert authToken to jwtAuthenticationToken (our token type)
		JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
		String token = authenticationToken.getToken();
		// validate token using validator & retrieve Our User
		JwtUser jwtUser = jwtValidator.validate(token);
		if (jwtUser == null) {
			throw new RuntimeException("token is not found");
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// is JwtAuthenticationToken is same or superclass/superinterface of authentication class
		return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
