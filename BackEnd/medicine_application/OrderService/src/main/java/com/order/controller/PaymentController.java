package com.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.bean.PaymentBean;
import com.order.exceptions.PaymentNotFoundException;
import com.order.service.PaymentService;

@RestController
@RequestMapping("/payment")
@CrossOrigin("*")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	private static Logger log = LoggerFactory.getLogger(PaymentController.class.getSimpleName());

	@GetMapping("/get/all")
	public ResponseEntity<List<PaymentBean>> getAllPayments() {
		log.info("PaymentController::getPayments::Started");
		List<PaymentBean> payments;
		try {
			payments = paymentService.getAllPayments();
			log.info("PaymentController::getPayments::Ended");
			return new ResponseEntity<List<PaymentBean>>(payments, HttpStatus.FOUND);
		} catch (PaymentNotFoundException e) {
			log.error("PaymentController::getPayments::"+e.getMessage());
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}
		

	}

}
