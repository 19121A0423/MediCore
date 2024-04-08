package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entity.RefreshToken;
import com.user.entity.User;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUser(User user);
}
