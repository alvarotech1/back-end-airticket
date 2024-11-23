package com.proyecto.airticket.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.proyecto.airticket.repositories.RefreshTokenRepository;
import com.proyecto.airticket.repositories.UserRepository;
import com.proyecto.airticket.user.Users;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

   
   private final RefreshTokenRepository refreshTokenRepository;
  
   private final UserRepository userRepository;
    
    private String hashToken(String token) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(token.getBytes());
        return Base64.getEncoder().encodeToString(encodedHash);
    }
    

    public RefreshToken createRefreshToken(String username){
    	 Users user = userRepository.findByUsername(username)
                 .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    	 
    	 String token = UUID.randomUUID().toString();
         String hashedToken = null;
         try {
             hashedToken = hashToken(token);
         } catch (NoSuchAlgorithmException e) {
             throw new RuntimeException("Error generating hash for token", e);
         }
    	 
        RefreshToken refreshToken = RefreshToken.builder()
                .users(user)
                .token(hashedToken)
                .expiryDate(Instant.now().plusMillis(600000))
                .build();
        refreshTokenRepository.save(refreshToken);
        
     // Devuelve el token sin hashear
        refreshToken.setToken(token);
        return refreshToken;
        
    }



    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }

}