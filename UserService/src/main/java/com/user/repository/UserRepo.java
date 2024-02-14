package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.user.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>  {

	User findByEmailAndPassword(String email, String password);
	
	User findByEmail(String email);
	
}
