package com.practice.jwtsecurity.model;

import lombok.Data;

@Data
public class JwtUser {
	private String username;
	private Long id;
	private String role;
}
