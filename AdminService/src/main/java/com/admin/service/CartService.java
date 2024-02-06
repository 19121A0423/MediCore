package com.admin.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.admin.bean.Cart;
import com.admin.bean.Product;
import com.admin.entity.CartEntity;
import com.admin.exception.CartIdNotFoundException;
import com.admin.exception.CartListNotFoundException;
import com.admin.exception.UserIdNotFoundException;
import com.admin.structure.ResponseStructure;

public interface CartService {

	public ResponseEntity<ResponseStructure<Cart>> save(Cart cart,int quantity) throws UserIdNotFoundException;
	public ResponseEntity<ResponseStructure<Cart>> update(Cart cart,int quantity) throws CartIdNotFoundException;
	public ResponseEntity<ResponseStructure<Cart>> delete(Integer cartId) throws CartIdNotFoundException;
	public ResponseEntity<ResponseStructure<List<Cart>>> getCartDetails() throws CartListNotFoundException;
	
	public ResponseEntity<ResponseStructure<Cart>>  getCartById(Integer cartId) throws CartIdNotFoundException;	
	
}
