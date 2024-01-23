package com.ooms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ooms.entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {

}
