package com.order.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.order.bean.PaymentBean;
import com.order.controller.PaymentController;
import com.order.exceptions.PaymentNotFoundException;
import com.order.service.PaymentService;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

	@Mock
	private PaymentService paymentService;

	@InjectMocks
	private PaymentController paymentController;

	@Test
	void testGetAllPayments() throws PaymentNotFoundException {
		PaymentBean payment1 = new PaymentBean();
        payment1.setPaymentId(1);
        payment1.setPaymentMode("upi");
        payment1.setAmount(999.0);
        payment1.setStatus("success");
        payment1.setPaymentDate(LocalDateTime.now());

        PaymentBean payment2 = new PaymentBean();
        payment2.setPaymentId(2);
        payment2.setPaymentMode("debitCard");
        payment2.setAmount(455.0);
        payment2.setStatus("success");
        payment2.setPaymentDate(LocalDateTime.now());

        List<PaymentBean> expectedPayments = Arrays.asList(payment1, payment2);
        when(paymentService.getAllPayments()).thenReturn(expectedPayments);
		
		ResponseEntity<List<PaymentBean>> responseEntity = paymentController.getAllPayments();

		assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
		assertEquals(expectedPayments, responseEntity.getBody());
	}
}
