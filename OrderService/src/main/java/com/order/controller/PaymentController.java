package com.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	private static Logger log = LoggerFactory.getLogger(PaymentController.class.getSimpleName());

//	@PostMapping("/save")
//	public ResponseEntity<Payment> savePayment(@RequestBody Payment payment) {
////		paymentService.save(payment);
//		return new ResponseEntity<Payment>(payment, HttpStatus.CREATED);
//	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable(value = "id") int id) {
		log.info("PaymentController::getPaymentById::Started");
		log.info("PaymentId : "+ id);
		try {
			Payment payment = paymentService.getPaymentById(id);
			log.info("PaymentController::getPaymentById::Ended");
			return new ResponseEntity<>(payment, HttpStatus.OK);
		} catch (PaymentNotFoundException e) {
			log.error("PaymentController::getPaymentById::"+e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/get/all")
	public ResponseEntity<List<Payment>> getPayments() {
		log.info("PaymentController::getPayments::Started");
		List<Payment> payments;
		try {
			payments = paymentService.getAllPayments();
			log.info("PaymentController::getPayments::Ended");
			return new ResponseEntity<List<Payment>>(payments, HttpStatus.FOUND);
		} catch (PaymentNotFoundException e) {
			log.error("PaymentController::getPayments::"+e.getMessage());
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}
		

	}

}
