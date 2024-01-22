package com.order.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.bean.Order;
import com.order.entity.OrderEntity;
import com.order.exceptions.OrderNotFoundException;
import com.order.repository.OrderRepository;
import com.order.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public void save(Order order) {
		OrderEntity orderEntity = new OrderEntity();
		beanToEntity(order, orderEntity);
		orderRepository.save(orderEntity);
		
	}
	
	@Override
	public Order findById(int id) {
		Optional<OrderEntity> orderEntity = orderRepository.findById(id);
		Order order = new Order();
		entityToBean(order, orderEntity.get());
		return order;
	}
	
	@Override
	public List<Order> findAll() {
		List<OrderEntity> orderEntities = orderRepository.findAll();
		List<Order> orders = new ArrayList<>();
		entitiesToBeans(orders, orderEntities);
		return orders;
		
	}
	
	@Override
	public void delete(int id) throws OrderNotFoundException {
		if(orderRepository.existsById(id)) {
			orderRepository.updateStatusById(id);
		}else {
			throw new OrderNotFoundException("Order not found");
		}
		
	}
	
	public void beanToEntity(Order order , OrderEntity orderEntity) {
		orderEntity.setAddressId(order.getAddressId());
		orderEntity.setCartId(order.getCartId());
		orderEntity.setOrderedDate(null);
		orderEntity.setStatus("in progress");
		
	}
	
	public void entityToBean(Order order , OrderEntity orderEntity) {
		order.setOrderId(orderEntity.getOrderId());
		order.setAddressId(orderEntity.getAddressId());
		order.setCartId(orderEntity.getCartId());
		order.setOrderedDate(orderEntity.getOrderedDate());
		order.setStatus(orderEntity.getStatus());
		
	}
	
	public void entitiesToBeans(List<Order> orders , List<OrderEntity> orderEntities) {
		
		orderEntities.stream().forEach(orderEntity -> {
			Order order = new Order();
			order.setOrderId(orderEntity.getOrderId());
			order.setAddressId(orderEntity.getAddressId());
			order.setCartId(orderEntity.getCartId());
			order.setOrderedDate(orderEntity.getOrderedDate());
			order.setStatus(orderEntity.getStatus());
			
			orders.add(order);
		});
		
	}

}
