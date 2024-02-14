package com.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.order.exceptions.AddressNotFoundException;
import com.order.exceptions.OrderNotFoundException;
import com.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	private static Logger log = LoggerFactory.getLogger(OrderController.class.getSimpleName());

	@PostMapping("/save")
	public ResponseEntity<Orders> saveOrder(@RequestBody Orders order) {
		log.info("OrderController::saveOrder::Started");
//		log.info("Order : "+order);
		try {
			order = orderService.placeOrder(order);
			log.info("OrderController::saveOrder::Ended");
			return new ResponseEntity<Orders>(order, HttpStatus.CREATED);
		} catch (IllegalArgumentException | AddressNotFoundException e) {
			log.error("OrderController::saveOrder::" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Orders> getOrderById(@PathVariable(value = "id") int id) {
		log.info("OrderController::getOrderById::Started");
		log.info("OrderId : " + id);
		try {
			Orders order = orderService.getOrderById(id);
			log.info("OrderController::getOrderById::Ended");
			return new ResponseEntity<>(order, HttpStatus.OK);
		} catch (OrderNotFoundException e) {
			log.error("OrderController::getOrderById::" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/get/all")
	public ResponseEntity<List<Orders>> getOrders() {
		log.info("OrderController::getOrders::Started");
		List<Orders> orders;
		try {
			orders = orderService.getAllOrders();
			log.info("OrderController::getOrders::Ended");
			return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
		} catch (OrderNotFoundException e) {
			log.error("OrderController::getOrders::" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateOrderById(@PathVariable(value = "id") int id) {
		log.info("OrderController::updateOrderById::Started");
		log.info("OrderId : " + id);
		try {
			orderService.updateStatusById(id);
			log.info("OrderController::updateOrderById::Ended");
			return new ResponseEntity<>("Successfully deleted order of id: " + id, HttpStatus.OK);
		} catch (OrderNotFoundException e) {
			log.error("OrderController::updateOrderById::" + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
