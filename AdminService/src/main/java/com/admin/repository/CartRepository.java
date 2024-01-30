package com.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admin.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Integer>{

}
