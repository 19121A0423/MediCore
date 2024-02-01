package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.bean.Orders;
import com.order.exceptions.OrderNotFoundException;
import com.order.service.OrderService;


@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/save")
	public ResponseEntity<Orders> saveOrder(@RequestBody Orders order){
	    try {
	    	orderService.placeOrder(order);
		    return new ResponseEntity<Orders>(order, HttpStatus.CREATED);
	    }
	    catch(IllegalArgumentException e) {
	    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Orders> getAddressById(@PathVariable(value = "id") int id){
		try {
	        Orders order = orderService.getOrderById(id);
	        return new ResponseEntity<>(order, HttpStatus.OK);
	    } catch (OrderNotFoundException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
		
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<List<Orders>> getOrders(){
		List<Orders> orders;
		try {
			orders = orderService.getAllOrders();
			return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
		} catch (OrderNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateOrderById(@PathVariable(value = "id") int id) {
	    try {
	        orderService.updateStatusById(id);
	        return new ResponseEntity<>("Successfully deleted order of id: " + id, HttpStatus.OK);
	    } catch (OrderNotFoundException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	    }
	}
}
