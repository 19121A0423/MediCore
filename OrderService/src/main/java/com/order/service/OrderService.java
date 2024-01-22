package com.order.service;

import java.util.List;

import com.order.bean.Order;
import com.order.exceptions.OrderNotFoundException;

public interface OrderService {
	
	void save(Order order);

	Order findById(int id);

	List<Order> findAll();

	void delete(int id) throws OrderNotFoundException;

}
