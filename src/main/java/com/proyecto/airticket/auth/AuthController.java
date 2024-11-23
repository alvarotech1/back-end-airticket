package com.proyecto.airticket.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.airticket.jwt.JwtService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
	
	private final AuthService authService;
	private final RefreshTokenService refreshTokenService;
	private final JwtService jwtService;

	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		
		try {
			return ResponseEntity.ok(authService.login(request));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest request){
		try {
			authService.register(request);
			return ResponseEntity.ok("Registro exitoso");
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
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
	                        .refreshToken(refreshTokenRequest.getToken()).build();
	            }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
	}
}
