package com.order.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.bean.OrderBean;
import com.order.bean.PaymentBean;
import com.order.entity.Orders;
import com.order.entity.Payment;
import com.order.exceptions.PaymentNotFoundException;
import com.order.repository.PaymentRepository;
import com.order.service.OrderService;
import com.order.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	private static Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class.getSimpleName());

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ObjectMapper mapper;

	@Override
	public PaymentBean savePayment(PaymentBean paymentBean,Orders orderEntity) {
		log.info("PaymentServiceImpl::savePayment::Started");
	    if (paymentBean.getAmount() <= 0 && orderEntity.getOrderId() <= 0 && paymentBean.getPaymentMode().isEmpty() ) {
	        throw new IllegalArgumentException("Invalid payment information");
	    }
	    
	    else {
	    	Payment payment = new Payment();
	    	payment = mapper.convertValue(paymentBean, Payment.class);
	    	payment.setPaymentDate(LocalDateTime.now());
		    payment.setOrder(orderEntity);
		    paymentRepository.save(payment);
		    paymentBean = mapper.convertValue(payment, PaymentBean.class);
		    log.info("PaymentServiceImpl::savePayment::Ended");
		    return paymentBean;
	    }
	}

	@Override
	public List<PaymentBean> getAllPayments() throws PaymentNotFoundException {
		log.info("PaymentServiceImpl::getAllPayments::Started");
		List<Payment> payments = paymentRepository.findAll();
		if(payments.isEmpty()) {
			throw new PaymentNotFoundException("No payments found");
		}
		else {
			List<PaymentBean> paymentBeans = new ArrayList<>();
			payments.stream().forEach(payment -> {
				PaymentBean paymentBean = new PaymentBean();
				paymentBean = mapper.convertValue(payment, PaymentBean.class);
				
				OrderBean orderBean = new OrderBean();
				orderBean = mapper.convertValue(payment.getOrder(), OrderBean.class);
				paymentBean.setOrder(orderBean);
				paymentBeans.add(paymentBean);
			});
			log.info("PaymentServiceImpl::getAllPayments::Ended");
			return paymentBeans;
		}
	}
	
}
