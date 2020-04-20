package com.practice.jwtsecurity.security;

import com.practice.jwtsecurity.exception.JwtAuthenticationException;
import com.practice.jwtsecurity.model.JwtAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

//	private static final String defaultFilterProcessesUrl = "/**";

	// how all the url's are going to be coming
	// here we'll authenticate only api with string "rest"
	public JwtAuthenticationTokenFilter() {
		super("/rest/**");
	}

	// Here we authorize & handle authentication request validation
	// Here we decode the token
	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
	                                            HttpServletResponse httpServletResponse)
			throws AuthenticationException, IOException, ServletException {
		String header = httpServletRequest.getHeader("Authorization");
		if (header == null || !header.startsWith("jwt:")) {
			throw new JwtAuthenticationException("Jwt Token is missing.");
		}
		String authToken = header.substring(4);
		log.debug("auth-token: {}", authToken);
		JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(authToken);
		return getAuthenticationManager().authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		log.debug("Successful authentication");
		chain.doFilter(request, response);
	}
}
