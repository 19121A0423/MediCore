package com.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.admin.bean.Cart;
import com.admin.service.CartService;

@RestController
public class CartController {
	
	private static Logger log = LoggerFactory
			.getLogger(CartController.class.getSimpleName());
	
	
	@Autowired
	private CartService service;
	
	
	@PostMapping("/cart/{quantity}")
	public ResponseEntity<Cart> saveCart(@RequestBody Cart cart, @PathVariable int quantity){		
		log.info("CartController Start => {} ",cart);
		ResponseEntity<Cart> responseEntity = null;
		try {
		responseEntity = service.save(cart,quantity);
		log.info("CartController End => {} ",cart);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return responseEntity ;	

	}
	
}
