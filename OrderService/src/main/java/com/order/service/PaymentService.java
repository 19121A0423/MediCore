package com.order.service;

import java.util.List;

import com.order.bean.Payment;
import com.order.entity.OrderEntity;
import com.order.exceptions.PaymentNotFoundException;

public interface PaymentService {
	
	void save(Payment payment, OrderEntity orderEntity);

	Payment findById(int id) throws PaymentNotFoundException;

	List<Payment> findAll();
	
}
