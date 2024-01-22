package com.order.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.bean.Payment;
import com.order.entity.PaymentEntity;
import com.order.exceptions.PaymentNotFoundException;
import com.order.repository.PaymentRepository;
import com.order.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public void save(Payment payment) {
		PaymentEntity paymentEntity = new PaymentEntity();
		beanToEntity(payment, paymentEntity);
		paymentRepository.save(paymentEntity);

	}

	@Override
	public Payment findById(int id) {
		Optional<PaymentEntity> paymentEntity = paymentRepository.findById(id);
		Payment payment = new Payment();
		entityToBean(payment, paymentEntity.get());
		return payment;
	}

	@Override
	public List<Payment> findAll() {
		List<PaymentEntity> paymentEntities = paymentRepository.findAll();
		List<Payment> payments = new ArrayList<>();
		entitiesToBeans(payments, paymentEntities);
		return payments;
	}

//	@Override
//	public void delete(int id) throws PaymentNotFoundException {
//		if(paymentRepository.existsById(id)) {
//			paymentRepository.updateStatusById(id);
//		}else {
//			throw new PaymentNotFoundException();
//		}
//
//	}

	public void beanToEntity(Payment payment, PaymentEntity paymentEntity) {

		paymentEntity.setAmount(payment.getAmount());
		paymentEntity.setOrderId(payment.getOrderId());
//		paymentEntity.setPaymentDate();
		paymentEntity.setPaymentMode(payment.getPaymentMode());
		paymentEntity.setStatus(payment.getStatus());

	}

	public void entityToBean(Payment payment, PaymentEntity paymentEntity) {
		
		payment.setPaymentId(paymentEntity.getPaymentId());
		payment.setAmount(paymentEntity.getAmount());
		payment.setOrderId(paymentEntity.getOrderId());
		payment.setPaymentDate(paymentEntity.getPaymentDate());
		payment.setPaymentMode(paymentEntity.getPaymentMode());
		payment.setStatus(paymentEntity.getStatus());

	}
	
	public void entitiesToBeans(List<Payment> payments , List<PaymentEntity> paymentEntities) {
		
		paymentEntities.stream().forEach(paymentEntity -> {
			Payment payment = new Payment();
			payment.setPaymentId(paymentEntity.getPaymentId());
			payment.setAmount(paymentEntity.getAmount());
			payment.setOrderId(paymentEntity.getOrderId());
			payment.setPaymentDate(paymentEntity.getPaymentDate());
			payment.setPaymentMode(paymentEntity.getPaymentMode());
			payment.setStatus(paymentEntity.getStatus());
			
			payments.add(payment);
			
		});
		
	}

}
