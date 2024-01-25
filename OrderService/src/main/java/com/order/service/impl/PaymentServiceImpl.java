package com.order.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.bean.Orders;
import com.order.bean.Payment;
import com.order.entity.OrderEntity;
import com.order.entity.PaymentEntity;
import com.order.exceptions.PaymentNotFoundException;
import com.order.repository.PaymentRepository;
import com.order.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	private OrderServiceImpl orderService = new OrderServiceImpl();

	@Override
	public void save(Payment payment,OrderEntity orderEntity) {
	    if (payment.getAmount() <= 0 ) {
	        throw new IllegalArgumentException("Invalid payment information");
	    }
	    
	    PaymentEntity paymentEntity = new PaymentEntity();
	    paymentEntity.setOrder(orderEntity);
	    beanToEntity(payment, paymentEntity);
	    paymentRepository.save(paymentEntity);
	}


	@Override
	public Payment findById(int id) throws PaymentNotFoundException {
		PaymentEntity paymentEntity = paymentRepository.findById(id)
				.orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID: " + id));

		Payment payment = new Payment();
		entityToBean(payment, paymentEntity);
		return payment;
	}

	@Override
	public List<Payment> findAll() {
		List<PaymentEntity> paymentEntities = paymentRepository.findAll();
		List<Payment> payments = new ArrayList<>();
		entitiesToBeans(payments, paymentEntities);
		return payments;
	}

	public void beanToEntity(Payment payment, PaymentEntity paymentEntity) {

		paymentEntity.setAmount(payment.getAmount());
		paymentEntity.setPaymentDate(LocalDate.now());
		paymentEntity.setPaymentMode(payment.getPaymentMode());
		paymentEntity.setStatus(payment.getStatus());

	}

	public void entityToBean(Payment payment, PaymentEntity paymentEntity) {
		
		payment.setPaymentId(paymentEntity.getPaymentId());
		payment.setAmount(paymentEntity.getAmount());
		Orders order = new Orders();
		orderService.entityToBean(order, paymentEntity.getOrder());
		payment.setPaymentDate(paymentEntity.getPaymentDate());
		payment.setPaymentMode(paymentEntity.getPaymentMode());
		payment.setStatus(paymentEntity.getStatus());

	}
	
	public void entitiesToBeans(List<Payment> payments , List<PaymentEntity> paymentEntities) {
		
		paymentEntities.stream().forEach(paymentEntity -> {
			Payment payment = new Payment();
			payment.setPaymentId(paymentEntity.getPaymentId());
			payment.setAmount(paymentEntity.getAmount());
			Orders order = new Orders();
			orderService.entityToBean(order, paymentEntity.getOrder());
			payment.setPaymentDate(paymentEntity.getPaymentDate());
			payment.setPaymentMode(paymentEntity.getPaymentMode());
			payment.setStatus(paymentEntity.getStatus());
			
			payments.add(payment);
			
		});
		
	}
	
}
