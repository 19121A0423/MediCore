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

import com.order.bean.Payment;
import com.order.exceptions.PaymentNotFoundException;
import com.order.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/save")
	public ResponseEntity<Payment> save(@RequestBody Payment payment){
		paymentService.save(payment);
		return new ResponseEntity<Payment>(payment,HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Payment> getAddressById(@PathVariable(value = "id") int id){
//		System.out.println("controller started");
		Payment payment = paymentService.findById(id);
//		System.out.println("controller ended");
		return new ResponseEntity<Payment>(payment, HttpStatus.FOUND);
		
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<List<Payment>> getPayments(){
//		System.out.println("controller started");
		List<Payment> payments = paymentService.findAll();
//		System.out.println("controller ended");
		return new ResponseEntity<List<Payment>>(payments, HttpStatus.FOUND);
		
	}
	
//	@DeleteMapping("/delete/{id}")
//	public String deleteAddressById(@PathVariable(value="id") int id) {
//		try {
//			paymentService.delete(id);
//			return "Successfully deleted address of id : "+id ;
//		} catch (PaymentNotFoundException e) {
////			e.printStackTrace();
//			return e.getMessage();
//		}
//	}

}
