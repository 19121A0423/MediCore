package com.admin.service;

import org.springframework.stereotype.Service;

import com.admin.bean.Cart;
import com.admin.entity.CartEntity;

@Service
public interface CartService {
	
	public void insert(Cart cart);
	
	public CartEntity get(Integer cart_id);
	
}
