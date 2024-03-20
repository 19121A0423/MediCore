package com.order.service;

import java.util.List;

import com.order.bean.PaymentBean;
import com.order.entity.Orders;
import com.order.exceptions.PaymentNotFoundException;

public interface PaymentService {
	
	PaymentBean savePayment(PaymentBean payment, Orders orderEntity);

	List<PaymentBean> getAllPayments() throws PaymentNotFoundException;
	
}
