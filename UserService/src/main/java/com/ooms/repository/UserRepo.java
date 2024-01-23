package com.ooms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ooms.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>  {

}
