package com.practice.jwtsecurity.security;

import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;

@Data
public class JwtAuthenticationTokenFilter {
	private AuthenticationManager authenticationManager;
	private JwtSuccessHandler authenticationSuccessHandler;
}
