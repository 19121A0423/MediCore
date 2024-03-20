package com.order.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.order.bean.AddressBean;
import com.order.bean.OrderBean;
import com.order.bean.PaymentBean;
import com.order.controller.OrderController;
import com.order.exceptions.AddressNotFoundException;
import com.order.exceptions.OrderNotFoundException;
import com.order.service.OrderService;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

	@Mock
	private OrderService orderService;

	@InjectMocks
	private OrderController orderController;

	@Test
    void testPlaceOrder() throws AddressNotFoundException {
        
		OrderBean orderBean = new OrderBean();
        orderBean.setOrderId(1);
        orderBean.setOrderedDate(LocalDateTime.now());
        orderBean.setStatus("delivered");
        AddressBean address = new AddressBean();
        address.setAddressId(2);
        address.setStreetName("Sri Sailakshmi Boys Hostel, Anantapur Tirupati Chennai Highway, Chandragiri Subdistrict");
        address.setCity("Tirupati");
        address.setState("Andhra Pradesh");
        address.setPinCode(517102l);
        address.setUserId(1);
        address.setStatus("active");
        orderBean.setAddress(address);
        orderBean.setCartId(1);
        PaymentBean paymentBean = new PaymentBean();
        paymentBean.setPaymentId(1);
        paymentBean.setPaymentMode("upi");
        paymentBean.setAmount(999.0);
        paymentBean.setStatus("success");
        paymentBean.setPaymentDate(LocalDateTime.now());

        orderBean.setPayment(paymentBean);

        when(orderService.placeOrder(any(OrderBean.class))).thenReturn(orderBean);
        
        ResponseEntity<OrderBean> response = orderController.saveOrder(orderBean);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderBean, response.getBody());

    }
	
	@Test
	void testGetAllOrders() throws OrderNotFoundException {

		OrderBean orderBean = new OrderBean();
        orderBean.setOrderId(1);
        orderBean.setOrderedDate(LocalDateTime.now());
        orderBean.setStatus("delivered");
        AddressBean address = new AddressBean();
        address.setAddressId(2);
        address.setStreetName("Sri Sailakshmi Boys Hostel, Anantapur Tirupati Chennai Highway, Chandragiri Subdistrict");
        address.setCity("Tirupati");
        address.setState("Andhra Pradesh");
        address.setPinCode(517102l);
        address.setUserId(1);
        address.setStatus("active");
        orderBean.setAddress(address);
        orderBean.setCartId(1);
        PaymentBean paymentBean = new PaymentBean();
        paymentBean.setPaymentId(1);
        paymentBean.setPaymentMode("upi");
        paymentBean.setAmount(999.0);
        paymentBean.setStatus("success");
        paymentBean.setPaymentDate(LocalDateTime.now());

        orderBean.setPayment(paymentBean);
        List<OrderBean> orders = new ArrayList<>();
        orders.add(orderBean);

		when(orderService.getAllOrders()).thenReturn(orders);

		ResponseEntity<List<OrderBean>> responseEntity = orderController.getOrders();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(orders, responseEntity.getBody());
	}
	
}
