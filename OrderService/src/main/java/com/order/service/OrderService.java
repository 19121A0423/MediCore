package com.order.service;

import java.util.List;

import com.order.bean.Cart;
import com.order.bean.Orders;
import com.order.entity.OrderEntity;
import com.order.exceptions.AddressNotFoundException;
import com.order.exceptions.CartNotFoundException;
import com.order.exceptions.OrderNotFoundException;

public interface OrderService {
	
	Orders placeOrder(Orders order) throws AddressNotFoundException;

	Orders getOrderById(int id) throws OrderNotFoundException;

	List<Orders> getAllOrders() throws OrderNotFoundException;

	void updateStatusById(int id) throws OrderNotFoundException;

	Cart getCart(int id) throws CartNotFoundException;

	void beanToEntity(Orders order, OrderEntity orderEntity);

	void entityToBean(Orders order, OrderEntity orderEntity);

	void entitiesToBeans(List<Orders> orders, List<OrderEntity> orderEntities);

}
