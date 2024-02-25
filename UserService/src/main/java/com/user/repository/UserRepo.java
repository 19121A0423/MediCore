package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.user.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>  {

	User findByEmailAndPassword(String email, String password);
	
	User findByEmail(String email);
	
	
	
	@Query("select u.email from User u where u.email=?1")
	public String getEmail(String userEmail);
		
	@Query("select u.mobile_number from User u where u.mobile_number=?1")
	public Long getMobileNumber(long mobileNumber);
	
}
