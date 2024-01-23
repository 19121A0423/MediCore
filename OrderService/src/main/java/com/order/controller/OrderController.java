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
	public ResponseEntity<Orders> save(@RequestBody Orders order){
	    System.out.println("Received Order: " + order);
	    orderService.save(order);
	    System.out.println("Order saved successfully: " + order);
	    return new ResponseEntity<Orders>(order, HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Orders> getAddressById(@PathVariable(value = "id") int id){
		try {
	        Orders order = orderService.findById(id);
	        return new ResponseEntity<>(order, HttpStatus.OK);
	    } catch (OrderNotFoundException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
		
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<List<Orders>> getOrders(){
		List<Orders> orders = orderService.findAll();
		return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
		
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
