package com.practice.jwtsecurity.config;

import com.practice.jwtsecurity.security.JwtAuthenticationEntryPoint;
import com.practice.jwtsecurity.security.JwtAuthenticationProvider;
import com.practice.jwtsecurity.security.JwtAuthenticationTokenFilter;
import com.practice.jwtsecurity.security.JwtSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Collections;

// for method level security
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationProvider authenticationProvider;

	@Autowired
	private JwtAuthenticationEntryPoint exceptionEntryPoint;

	// This is the filter.
	@Bean("jwtAuthTokenFilter")
	public JwtAuthenticationTokenFilter authenticationTokenFilter() {
		JwtAuthenticationTokenFilter authFilter = new JwtAuthenticationTokenFilter();
		authFilter.setAuthenticationManager(authenticationManager());
		// after success some processing
		authFilter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
		return authFilter;
	}

	// This is the authentication Manager that provides auth-provider
	@Bean("jwtAuthManager")
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(Collections.singletonList(authenticationProvider));
	}

	// Http Security Filter configure
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// csrf token disable
				.csrf().disable()
				// any req api having "/rest/" will be authenticated
				.authorizeRequests().antMatchers("**/rest/**")
				.authenticated()
				.and()
				// If not authenticated, where to redirect
				.exceptionHandling().authenticationEntryPoint(exceptionEntryPoint)
	}
}

