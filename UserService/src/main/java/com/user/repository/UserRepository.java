package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>  {

	User findByUserEmailAndUserPassword(String userEmail, String userPassword);
	
	User findByUserEmail(String userEmail);
	
	public User findByUserEmailOrUserMobileNumber(String userEmail,Long userMobileNumber);
	
}
