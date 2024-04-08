package com.user.serviceImpl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.RefreshToken;
import com.user.entity.User;
import com.user.exception.EmailNotFoundException;
import com.user.repository.RefreshTokenRepository;
import com.user.repository.UserRepository;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    
    @Autowired
    private UserRepository userRepository;

    public RefreshToken createRefreshToken(String email) {
        Optional<User> userOptional = userRepository.findByUserEmail(email);
        if (userOptional.isPresent()) {
            Optional<RefreshToken> existingTokenOptional = refreshTokenRepository.findByUser(userOptional.get());
            if (existingTokenOptional.isPresent()) {
                return existingTokenOptional.get();
            }
        } else {
            throw new EmailNotFoundException("User not found for email: " + email);
        }
        
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userOptional.get())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusSeconds(604800)) // 7 days (7 * 24 * 60 * 60 seconds)
                .build();
        return refreshTokenRepository.save(refreshToken);
    }



    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }


    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

}
