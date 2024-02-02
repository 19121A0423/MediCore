package com.order.service;

import java.util.List;

import com.order.bean.Payment;
import com.order.entity.OrderEntity;
import com.order.exceptions.PaymentNotFoundException;

public interface PaymentService {
	
	void savePayment(Payment payment, OrderEntity orderEntity);

	Payment getPaymentById(int id) throws PaymentNotFoundException;

	List<Payment> getAllPayments() throws PaymentNotFoundException;
	
}
