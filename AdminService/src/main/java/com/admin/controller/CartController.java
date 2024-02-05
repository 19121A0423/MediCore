package com.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.admin.bean.Cart;
import com.admin.exception.CartIdNotFoundException;
import com.admin.exception.CartListNotFoundException;
import com.admin.exception.UserIdNotFoundException;
import com.admin.service.CartService;
import com.admin.structure.ResponseStructure;

@RestController
public class CartController {
	
	private static Logger log = LoggerFactory
			.getLogger(CartController.class.getSimpleName());
	
	
	@Autowired
	private CartService service;
	
	
	@PostMapping("/cart/{quantity}")
	public ResponseEntity<ResponseStructure<Cart>> saveCart(@RequestBody Cart cart, @PathVariable int quantity) throws UserIdNotFoundException{		
		log.info("CartController saveCart Start => {} ",cart);
		 ResponseEntity<ResponseStructure<Cart>> structure=null;
		try {
		  structure = service.save(cart,quantity);
		}catch (IllegalArgumentException e) {			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		 log.info("CartController End => {} ",cart);
		return structure;		
	}
	
	
	@GetMapping("/cart/{cartId}")
	public ResponseEntity<ResponseStructure<Cart>>  getCartById(@PathVariable Integer cartId) throws CartIdNotFoundException {
		log.info("CartController getCartById Start => {} ",cartId);
		 ResponseEntity<ResponseStructure<Cart>> structure=null;
		try {
			  structure = service.getCartById(cartId);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		log.info("CartController getCartById End => {} ",cartId);
		return structure;

	}
	
	@DeleteMapping("/cart/{cartId}")
	public ResponseEntity<ResponseStructure<Cart>> delete(@PathVariable Integer cartId) throws CartIdNotFoundException {
		
		log.info("Cart controller delete cart method start "+cartId);
		ResponseEntity<ResponseStructure<Cart>> structure = null;
		try {
			structure=service.delete(cartId);
			log.info("Cart controller delete cart method end try block {} "+structure.getBody().getData());
			return structure;
		}catch (IllegalArgumentException e) {
			log.info("Cart controller delete cart method end catch block {} "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@GetMapping("/cart")
	public ResponseEntity<ResponseStructure<List<Cart>>> getCartDetails() throws CartListNotFoundException{
		log.info("Cart controller getCartDetails method start");
		ResponseEntity<ResponseStructure<List<Cart>>> cartDetails =service.getCartDetails();
		log.info("Cart controller getCartDetails method End");
		return cartDetails;
	}

	
}
