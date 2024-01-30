package com.order.service;

import java.util.List;

import com.order.bean.Cart;
import com.order.bean.Orders;
import com.order.exceptions.OrderNotFoundException;

public interface OrderService {
	
	void placeOrder(Orders order);

	Orders findById(int id) throws OrderNotFoundException;

	List<Orders> findAll();

	void updateStatusById(int id) throws OrderNotFoundException;

	Cart getCart(int id);

}
