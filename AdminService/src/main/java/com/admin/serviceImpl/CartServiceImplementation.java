package com.admin.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.bean.Cart;
import com.admin.entity.CartEntity;
import com.admin.repository.CartRepository;
import com.admin.service.CartService;

@Service
public class CartServiceImplementation implements CartService{

	@Autowired
	private CartRepository cartRepository;
	
	
	@Override
	public void insert(Cart cart) {
		CartEntity cartEntity=new CartEntity();
		cartEntity.setCartId(cart.getCartId());
		
		
	}

	@Override
	public CartEntity get(Integer cart_id) {
		
		return cartRepository.findById(cart_id).get();
	}

}
