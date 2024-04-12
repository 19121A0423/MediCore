package com.order.service;

import java.util.List;

import com.order.bean.OrderBean;
import com.order.exceptions.AddressNotFoundException;
import com.order.exceptions.OrderNotFoundException;

public interface OrderService {
	
	OrderBean placeOrder(OrderBean order) throws AddressNotFoundException;

	OrderBean getOrderById(int id) throws OrderNotFoundException;

	List<OrderBean> getAllOrders() throws OrderNotFoundException;

}
