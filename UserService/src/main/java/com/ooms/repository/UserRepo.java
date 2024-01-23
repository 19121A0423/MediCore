package com.ooms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ooms.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>  {

	
		@Query("select u from User u where email=?1")
     	public	User getUserByEmail(String email);

}
