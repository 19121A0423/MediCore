package com.admin.service;

import java.util.List;


import com.admin.bean.CartBean;
import com.admin.exception.CartIdNotFoundException;
import com.admin.exception.CartListNotFoundException;
import com.admin.exception.UserIdNotFoundException;

public interface CartService {

	public CartBean saveCart(CartBean cart) throws UserIdNotFoundException;
	public CartBean update(CartBean cart,Integer productId) throws CartIdNotFoundException;
	public CartBean delete(Integer cartId,Integer productId) throws CartIdNotFoundException;
    List<CartBean> getCartDetails() throws CartListNotFoundException;	
	public CartBean getCartById(Integer cartId) throws CartIdNotFoundException, UserIdNotFoundException, CartListNotFoundException;	
	public CartBean updateCartStatus(CartBean cart) throws CartIdNotFoundException;
}
