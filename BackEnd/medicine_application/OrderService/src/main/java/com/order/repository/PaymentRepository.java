package com.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.order.entity.Payment;

@Transactional
public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	
}
