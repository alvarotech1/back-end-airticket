package com.proyecto.airticket.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.airticket.jwt.JwtService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	private final RefreshTokenService refreshTokenService;
	private final JwtService jwtService;
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginRequest request) {
		
		return ResponseEntity.ok(authService.login(request));
	}

	@PostMapping("/register")
	public ResponseEntity<JwtAuthResponse> register(@RequestBody RegisterRequest request){
		
		return ResponseEntity.ok(authService.register(request));
	}
	
	@PostMapping("/refreshToken")
	public JwtAuthResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
	    return refreshTokenService.findByToken(refreshTokenRequest.getToken())
	            .map(refreshTokenService::verifyExpiration)
	            .map(RefreshToken::getUsers)
	            .map(users -> {
	                String accessToken = jwtService.getToken(users);
	                return JwtAuthResponse.builder()
	                        .accessToken(accessToken)
	                        .token(refreshTokenRequest.getToken()).build();
	            }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
	}
}
