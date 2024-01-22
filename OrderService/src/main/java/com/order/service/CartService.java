package com.order.service;

import java.util.List;

import com.order.bean.Cart;
import com.order.exceptions.CartNotFoundException;

public interface CartService {
	
	void save(Cart cart);

	Cart findById(int id);

	List<Cart> findAll();

	void delete(int id) throws CartNotFoundException;

}
