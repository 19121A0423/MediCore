package com.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.user.entity.OTPEntity;

import jakarta.transaction.Transactional;

@Transactional
public interface OTPRepository extends JpaRepository<OTPEntity, Integer>  {
	
	Optional<OTPEntity> findByEmail(String userEmail);
	
	@Modifying
	@Query("DELETE FROM OTPEntity o WHERE TIMESTAMPDIFF(MINUTE, o.expirationTime, CURRENT_TIMESTAMP()) <> 2 AND o.id > 0")
	void deleteExpiredOtps();
}

