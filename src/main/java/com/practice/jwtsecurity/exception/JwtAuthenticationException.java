package com.practice.jwtsecurity.exception;

public class JwtAuthenticationException extends RuntimeException {

	public JwtAuthenticationException(String message) {
		super(message);
	}

	public JwtAuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
}
