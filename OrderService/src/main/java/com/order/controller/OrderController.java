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

import com.order.bean.Order;
import com.order.exceptions.OrderNotFoundException;
import com.order.service.OrderService;


@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/save")
	public ResponseEntity<Order> save(@RequestBody Order order){
		orderService.save(order);
		return new ResponseEntity<Order>(order,HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Order> getAddressById(@PathVariable(value = "id") int id){
//		System.out.println("controller started");
		Order order = orderService.findById(id);
//		System.out.println("controller ended");
		return new ResponseEntity<Order>(order, HttpStatus.FOUND);
		
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<List<Order>> getOrders(){
//		System.out.println("controller started");
		List<Order> orders = orderService.findAll();
//		System.out.println("controller ended");
		return new ResponseEntity<List<Order>>(orders, HttpStatus.FOUND);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteOrderById(@PathVariable(value="id") int id) {
		try {
			orderService.delete(id);
			return "Successfully deleted order of id : "+id ;
		} catch (OrderNotFoundException e) {
//			e.printStackTrace();
			return e.getMessage();
		}
	}

}
