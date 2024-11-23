package com.proyecto.airticket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.proyecto.airticket.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authProvider;
	
	
	@Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.headers(httpSecurityHeadersConfigurer -> {
				    httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
				 })
					.csrf(csrf ->
					csrf.disable()
							)
				.authorizeHttpRequests(authRequest->
					authRequest
						.requestMatchers("/auth/**").permitAll()
						.requestMatchers("/h2/**").permitAll()
						.requestMatchers("/api/**").permitAll()
						.anyRequest().authenticated()
						)
				.sessionManagement(sessionManager -> 
						sessionManager
							.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
}
