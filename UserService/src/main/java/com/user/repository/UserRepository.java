package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>  {

	UserEntity findByUserEmailAndUserPassword(String userEmail, String userPassword);
	
	UserEntity findByUserEmail(String userEmail);
	
	public UserEntity findByUserEmailOrUserMobileNumber(String userEmail,Long userMobileNumber);
	
}
