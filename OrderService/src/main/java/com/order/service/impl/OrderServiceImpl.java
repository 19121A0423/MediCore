package com.order.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.order.bean.Address;
import com.order.bean.Cart;
import com.order.bean.Orders;
import com.order.bean.Payment;
import com.order.bean.UserBean;
import com.order.entity.AddressEntity;
import com.order.entity.OrderEntity;
import com.order.exceptions.OrderNotFoundException;
import com.order.repository.OrderRepository;
import com.order.service.AddressService;
import com.order.service.OrderService;
import com.order.service.PaymentService;
import com.order.structure.ResponseStructure;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private AddressServiceImpl addressService;
	
	@Autowired
	private RestTemplate restTemplate;

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
	
	@Override
	public Cart getCart(int id) {

		String url = "http://localhost:8081/medicine/users/"+id ;
		
		ParameterizedTypeReference<ResponseStructure<Cart>> responseType =
		        new ParameterizedTypeReference<ResponseStructure<Cart>>() {};
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<ResponseStructure<Cart>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity,responseType);
		ResponseStructure<Cart> response = responseEntity.getBody();
		Cart cart = response.getData();
		
//		ResponseStructure<UserBean> response = restTemplate.getForObject(url, responseType);
//		System.out.println(response.getData());
		return cart;
	}

	public void beanToEntity(Orders order, OrderEntity orderEntity) {
		AddressEntity address = new AddressEntity();
		addressService.beanToEntity(order.getAddress(), address);
		orderEntity.setAddress(address);
		orderEntity.setCartId(order.getCartId());
		orderEntity.setOrderedDate(LocalDate.now());
		orderEntity.setStatus("in progress");
		

	}

	public void entityToBean(Orders order, OrderEntity orderEntity) {
		order.setOrderId(orderEntity.getOrderId());
		Address address = new Address();
		addressService.entityToBean(address, orderEntity.getAddress());
		order.setAddress(address);
		order.setCartId(orderEntity.getCartId());
		order.setOrderedDate(orderEntity.getOrderedDate());
		order.setStatus(orderEntity.getStatus());

	}

	public void entitiesToBeans(List<Orders> orders, List<OrderEntity> orderEntities) {

		orderEntities.stream().forEach(orderEntity -> {
			Orders order = new Orders();
			order.setOrderId(orderEntity.getOrderId());
			Address address = new Address();
			addressService.entityToBean(address, orderEntity.getAddress());
			order.setAddress(address);
			order.setCartId(orderEntity.getCartId());
			order.setOrderedDate(orderEntity.getOrderedDate());
			order.setStatus(orderEntity.getStatus());

			orders.add(order);
		});

	}

}
