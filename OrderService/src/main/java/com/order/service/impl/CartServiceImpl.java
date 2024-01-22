package com.order.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.bean.Cart;
import com.order.entity.CartEntity;
import com.order.exceptions.CartNotFoundException;
import com.order.repository.CartRepository;
import com.order.service.CartService;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public void save(Cart cart) {
		CartEntity cartEntity = new CartEntity();
		beanToEntity(cart, cartEntity);
		cartRepository.save(cartEntity);
		
	}
	
	@Override
	public Cart findById(int id) {
		Optional<CartEntity> cartEntity = cartRepository.findById(id);
		Cart cart = new Cart();
		entityToBean(cart, cartEntity.get());
		return cart;
	}
	
	@Override
	public List<Cart> findAll() {
		List<CartEntity> cartEntities = cartRepository.findAll();
		List<Cart> carts = new ArrayList<>();
		entitiesToBeans(carts, cartEntities);
		return carts;
		
	}

	@Override
	public void delete(int id) throws CartNotFoundException {
		if(cartRepository.existsById(id)) {
			cartRepository.updateStatusById(id);
		}else {
			throw new CartNotFoundException();
		}
		
	}
	
	public void beanToEntity(Cart cart, CartEntity cartEntity) {
		cartEntity.setProductId(cart.getProductId());
		cartEntity.setAmount(cart.getAmount());
		cartEntity.setQuantity(cart.getQuantity());
		cartEntity.setUserId(cart.getUserId());
		cartEntity.setStatus("active");
	}
	
	public void entityToBean(Cart cart, CartEntity cartEntity) {
		cart.setCartId(cartEntity.getCartId());
		cart.setProductId(cartEntity.getProductId());
		cart.setAmount(cartEntity.getAmount());
		cart.setQuantity(cartEntity.getQuantity());
		cart.setUserId(cartEntity.getUserId());
		cart.setStatus(cartEntity.getStatus());
	}
	
	public void entitiesToBeans(List<Cart> carts, List<CartEntity> cartEntities ) {
		
		
		cartEntities.stream().forEach(cartEntity -> {
			Cart cart = new Cart();
			cart.setCartId(cartEntity.getCartId());
			cart.setProductId(cartEntity.getProductId());
			cart.setAmount(cartEntity.getAmount());
			cart.setQuantity(cartEntity.getQuantity());
			cart.setUserId(cartEntity.getUserId());
			cart.setStatus(cartEntity.getStatus());
			
			carts.add(cart);
		});
		
	}

}
