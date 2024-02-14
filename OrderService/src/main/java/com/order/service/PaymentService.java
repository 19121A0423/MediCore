package com.order.service;

import java.util.List;

import com.order.bean.Payment;
import com.order.entity.OrderEntity;
import com.order.entity.PaymentEntity;
import com.order.exceptions.PaymentNotFoundException;

public interface PaymentService {
	
	Payment savePayment(Payment payment, OrderEntity orderEntity);

	Payment getPaymentById(int id) throws PaymentNotFoundException;

	List<Payment> getAllPayments() throws PaymentNotFoundException;

	void beanToEntity(Payment payment, PaymentEntity paymentEntity);

	void entityToBean(Payment payment, PaymentEntity paymentEntity);

	void entitiesToBeans(List<Payment> payments, List<PaymentEntity> paymentEntities);
	
}
