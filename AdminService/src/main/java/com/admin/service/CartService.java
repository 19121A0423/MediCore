package com.admin.service;

import java.util.List;


import com.admin.bean.Cart;
import com.admin.exception.CartIdNotFoundException;
import com.admin.exception.CartListNotFoundException;
import com.admin.exception.UserIdNotFoundException;

public interface CartService {

	public Cart save(Cart cart) throws UserIdNotFoundException;
	public Cart update(Cart cart,Integer productId) throws CartIdNotFoundException;
	public Cart delete(Integer cartId,Integer productId) throws CartIdNotFoundException;
    List<Cart> getCartDetails() throws CartListNotFoundException;	
	public Cart getCartById(Integer cartId) throws CartIdNotFoundException, UserIdNotFoundException, CartListNotFoundException;	
	
}
