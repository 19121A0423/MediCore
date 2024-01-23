package com.order.service;

import java.util.List;

import com.order.bean.Payment;
import com.order.exceptions.PaymentNotFoundException;

public interface PaymentService {
	
	void save(Payment payment);

	Payment findById(int id) throws PaymentNotFoundException;

	List<Payment> findAll();
}
