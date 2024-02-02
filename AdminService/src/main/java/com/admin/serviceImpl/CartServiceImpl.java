package com.admin.serviceImpl;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.admin.bean.Cart;
import com.admin.bean.Product;
import com.admin.bean.UserBean;
import com.admin.entity.CartEntity;
import com.admin.entity.ProductEntity;
import com.admin.repository.CartRepo;
import com.admin.repository.ProductRepository;
import com.admin.service.CartService;
import com.admin.service.ProductService;

@Service
public class CartServiceImpl implements CartService {
	
	
	
	@Autowired
	public CartRepo repo;
	
	
	@Autowired
	public ProductRepository productRepo;
	
	
	@Autowired
	public ProductServiceImplementation service;
	
	
	
	@Autowired
	public UserServiceImpl userService;
	

	@Override
	public ResponseEntity<Cart> save(Cart cart,int userId,int productId) {
	
		
		
		
		CartEntity cartEntity = new CartEntity();		
		ProductEntity productEntity = service.get(productId);
		System.out.println(productEntity);
		UserBean userBean = userService.getUserBean(userId);
		System.out.println(userBean);
		cart.setUser(userBean);		
		List<ProductEntity> products = new ArrayList<>();
		products.add(productEntity);
		List<Product> list = service.convert(products);		
		cart.setProducts(list);
		CartEntity entity = beanToEntity(cart , cartEntity);
		cartEntity = repo.save(entity);
		System.out.println("saved");
		entityToBean(cartEntity,cart);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
		
	}

	private void entityToBean(CartEntity cartEntity, Cart cart) {
		
		cart.setCartId(cartEntity.getCartId());
		cart.setStatus(cartEntity.getStatus());
		
		
		List<Product > products = new ArrayList<>();
		for(ProductEntity ele:cartEntity.getProducts()) {
			
			Product obj = new Product();
			obj.setDescription(ele.getDescription());
			obj.setName(ele.getName());
			obj.setPrice(ele.getPrice());
			obj.setProductId(ele.getProductId());
			obj.setCategory(ele.getCategory());
			
			products.add(obj);			
			
		}		
		cart.setQuantity(products.size());
		
		DoubleSummaryStatistics collect = products.stream()
				.collect(Collectors.summarizingDouble(Product::getPrice));		
		cart.setAmount(collect.getSum());
		cart.setProducts(products);
		
		
	}

	@Override
	public ResponseEntity<Cart> update(Cart cart) {
		return null;
	}

	@Override
	public ResponseEntity<Cart> delete(Integer cartId) {
		return null;
	}

	@Override
	public ResponseEntity<List<Cart>> getCartDetails() {
		return null;
	}

	@Override
	public ResponseEntity<Cart> getCartById(Integer cartId) {
		return null;
	}

	@Override
	public ResponseEntity<List<Product>> getProductsCartId(Integer cartId) {
		return null;
	}
	
	public CartEntity beanToEntity(Cart cart ,CartEntity cartEntity) {
		
		cartEntity.setStatus(cart.getStatus());
		cartEntity.setUserId(cart.getUser().getUserId());
		
		
		List<ProductEntity > products = new ArrayList<>();
		for(Product ele:cart.getProducts()) {
			
			ProductEntity obj = new ProductEntity();
			obj.setDescription(ele.getDescription());
			obj.setName(ele.getName());
			obj.setPrice(ele.getPrice());
			obj.setProductId(ele.getProductId());
			obj.setCategory(ele.getCategory());
			
			products.add(obj);			
			
		}		
		cartEntity.setQuantity(products.size());
		
		DoubleSummaryStatistics collect = products.stream()
				.collect(Collectors.summarizingDouble(ProductEntity::getPrice));		
		cartEntity.setAmount(collect.getSum());
		cartEntity.setProducts(products);
		
		return cartEntity;
	}
	
	

}
