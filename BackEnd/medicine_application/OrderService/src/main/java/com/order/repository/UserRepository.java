package com.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.entity.User;


public interface UserRepository extends JpaRepository<User, Integer>  {

	User findByUserEmailAndUserPassword(String userEmail, String userPassword);
	
	Optional<User> findByUserEmail(String userEmail);
	
	public List<User> findByUserEmailOrUserMobileNumber(String userEmail,Long userMobileNumber);

	Optional<User> findByUserName(String userName);
	
}
