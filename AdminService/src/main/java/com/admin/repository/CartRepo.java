package com.admin.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.admin.entity.CartEntity;

public interface CartRepo extends JpaRepository<CartEntity, Integer> {
	
	@Query("select c from CartEntity c where c.userId=?1")
	public CartEntity getCartByUserId(int userId);

}
