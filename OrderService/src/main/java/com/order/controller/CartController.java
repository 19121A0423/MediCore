package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.bean.Cart;
import com.order.exceptions.CartNotFoundException;
import com.order.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/save")
	public ResponseEntity<Cart> savecart(@RequestBody Cart cart){
		cartService.save(cart);
		return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Cart> get(@PathVariable(value = "id") int id){
//		System.out.println("controller started");
		Cart cart = cartService.findById(id);
//		System.out.println("controller ended");
		return new ResponseEntity<Cart>(cart, HttpStatus.FOUND);
		
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<List<Cart>> getCarts(){
//		System.out.println("controller started");
		List<Cart> carts = cartService.findAll();
//		System.out.println("controller ended");
		return new ResponseEntity<List<Cart>>(carts, HttpStatus.FOUND);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteCartById(@PathVariable(value="id") int id) {
		try {
			cartService.delete(id);
			return "Successfully deleted cart of id : "+id ;
		} catch (CartNotFoundException e) {
//			e.printStackTrace();
			return e.getMessage();
		}
	}

}
