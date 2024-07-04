package com.proyecto.airticket.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.airticket.jwt.JwtService;
import com.proyecto.airticket.user.Role;
import com.proyecto.airticket.user.Users;
import com.proyecto.airticket.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepository userRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	public AuthResponse login(LoginRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
		String token = jwtService.getToken(user);
		return AuthResponse.builder()
				.token(token)
				.build();
	}

	public AuthResponse register(RegisterRequest request) {
		Users user = Users.builder()
					.username(request.getUsername())
					.password(passwordEncoder.encode(request.getPassword()))
					.firstname(request.getFirstname())
					.lastname(request.getLastname())
					.country(request.getCountry())
					.role(Role.USER)
					.build();
		
		
		userRepository.save(user);
		
		return AuthResponse.builder()
				.token(jwtService.getToken(user))
				.build();
	}

}
