package com.practice.jwtsecurity.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    FilterChain chain,
	                                    Authentication authentication) throws IOException, ServletException {
		log.debug("Successfully Authentication");
		chain.doFilter(request, response);
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    Authentication authentication) throws IOException, ServletException {
		log.debug("Successfully Authentication");
	}
}
