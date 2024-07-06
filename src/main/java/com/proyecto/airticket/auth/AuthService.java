package com.proyecto.airticket.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.airticket.jwt.JwtService;
import com.proyecto.airticket.repositories.UserRepository;
import com.proyecto.airticket.user.Role;
import com.proyecto.airticket.user.Users;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepository userRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final RefreshTokenService refreshTokenService;

	public JwtAuthResponse login(LoginRequest request) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
	
		if(authentication.isAuthenticated()) {
			String token = jwtService.getToken(user);
			RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.getUsername());
		return JwtAuthResponse.builder()
				.token(refreshToken.getToken())
				.accessToken(token)
				.build();
		}else {
	        throw new UsernameNotFoundException("invalid user request..!!");
		}
	}

	public JwtAuthResponse register(RegisterRequest request) {
		Users user = Users.builder()
					.username(request.getUsername())
					.password(passwordEncoder.encode(request.getPassword()))
					.firstname(request.getFirstname())
					.lastname(request.getLastname())
					.country(request.getCountry())
					.role(Role.USER)
					.build();
		
		
		userRepository.save(user);
		
		return JwtAuthResponse.builder()
				.token(jwtService.getToken(user))
				.build();
	}

}
