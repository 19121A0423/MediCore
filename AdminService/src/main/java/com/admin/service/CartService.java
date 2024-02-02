package com.admin.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.admin.bean.Cart;
import com.admin.bean.Product;

public interface CartService {

	public ResponseEntity<Cart> save(Cart cart,int quantity);
	public ResponseEntity<Cart> update(Cart cart);
	public ResponseEntity<Cart> delete(Integer cartId);
	public ResponseEntity<List<Cart>> getCartDetails();
	
	public ResponseEntity<Cart> getCartById(Integer cartId);	
	public ResponseEntity<List<Product>> getProductsCartId(Integer cartId);
	
}
