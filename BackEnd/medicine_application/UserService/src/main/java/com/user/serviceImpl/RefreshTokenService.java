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

	/**
	 * Creates a new refresh token for the user.
	 * 
	 * @param email The email of the user.
	 * @return The RefreshToken object representing the created refresh token.
	 * @throws EmailNotFoundException if the user is not found by email.
	 */
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

		RefreshToken refreshToken = RefreshToken.builder().user(userOptional.get()).token(UUID.randomUUID().toString())
				.expiryDate(Instant.now().plusSeconds(604800)) // 7 days (7 * 24 * 60 * 60 seconds)
				.build();
		return refreshTokenRepository.save(refreshToken);
	}

	/**
	 * Retrieves a refresh token by its token string.
	 * 
	 * @param token The token string.
	 * @return An Optional containing the RefreshToken object if found, empty
	 *         otherwise.
	 */

	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	/**
	 * Verifies if the refresh token has expired.
	 * 
	 * @param token The refresh token to verify.
	 * @return The RefreshToken object if not expired.
	 * @throws RuntimeException if the token has expired.
	 */
	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new RuntimeException(
					token.getToken() + " Refresh token was expired. Please make a new signin request");
		}
		return token;
	}

}
