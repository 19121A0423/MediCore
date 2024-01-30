package com.order.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.bean.Orders;
import com.order.bean.Payment;
import com.order.entity.OrderEntity;
import com.order.exceptions.OrderNotFoundException;
import com.order.repository.OrderRepository;
import com.order.service.OrderService;
import com.order.service.PaymentService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentService paymentService;

	@Override
	public void placeOrder(Orders order) {
		if (order == null) {
			throw new IllegalArgumentException("Order cannot be null");
		}
		OrderEntity orderEntity = new OrderEntity();
		beanToEntity(order, orderEntity);
		orderRepository.save(orderEntity);

		Payment payment = order.getPayment();
		payment.setOrder(order);
		paymentService.save(payment,orderEntity);

	}

	@Override
	public Orders findById(int id) throws OrderNotFoundException {
		OrderEntity orderEntity = orderRepository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException("Address not found with ID: " + id));

		Orders order = new Orders();
		entityToBean(order, orderEntity);
		return order;
	}

	@Override
	public List<Orders> findAll() {
		List<OrderEntity> orderEntities = orderRepository.findAll();
		List<Orders> orders = new ArrayList<>();
		entitiesToBeans(orders, orderEntities);
		return orders;

	}

	@Override
	public void updateStatusById(int id) throws OrderNotFoundException {
		if (orderRepository.existsById(id)) {
			orderRepository.updateStatusById(id);
		} else {
			throw new OrderNotFoundException("Order not found with ID: " + id);
		}

	}

	public void beanToEntity(Orders order, OrderEntity orderEntity) {
		orderEntity.setAddressId(order.getAddressId());
		orderEntity.setCartId(order.getCartId());
		orderEntity.setOrderedDate(LocalDate.now());
		orderEntity.setStatus("in progress");
		

	}

	public void entityToBean(Orders order, OrderEntity orderEntity) {
		order.setOrderId(orderEntity.getOrderId());
		order.setAddressId(orderEntity.getAddressId());
		order.setCartId(orderEntity.getCartId());
		order.setOrderedDate(orderEntity.getOrderedDate());
		order.setStatus(orderEntity.getStatus());

	}

	public void entitiesToBeans(List<Orders> orders, List<OrderEntity> orderEntities) {

		orderEntities.stream().forEach(orderEntity -> {
			Orders order = new Orders();
			order.setOrderId(orderEntity.getOrderId());
			order.setAddressId(orderEntity.getAddressId());
			order.setCartId(orderEntity.getCartId());
			order.setOrderedDate(orderEntity.getOrderedDate());
			order.setStatus(orderEntity.getStatus());
			orders.add(order);
		});

	}

}
