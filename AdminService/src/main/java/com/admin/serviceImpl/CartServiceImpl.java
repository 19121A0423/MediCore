package com.admin.serviceImpl;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.admin.bean.Cart;
import com.admin.bean.Product;
import com.admin.bean.UserBean;
import com.admin.entity.CartEntity;
import com.admin.entity.ProductEntity;
import com.admin.exception.CartIdNotFoundException;
import com.admin.repository.CartRepo;
import com.admin.repository.ProductRepository;
import com.admin.service.CartService;
import com.admin.structure.ResponseStructure;

@Service
public class CartServiceImpl implements CartService {
	
	private static Logger log = LoggerFactory
			.getLogger(CartServiceImpl.class.getSimpleName());
	
	@Autowired
	public CartRepo repo;
	
	
	@Autowired
	public ProductRepository productRepo;
	
	
//	@Autowired
//	public ProductServiceImplementation service;
//	
	
	
	@Autowired
	public UserServiceImpl userService;
	

	@Override
	public ResponseEntity<Cart> save(Cart cart,int quantity) {
	
		log.info("Cart Service Implementation Save Method Start-> {}"+cart);
		
		 CartEntity cartEntity = repo.getCartByUserId(cart.getUser().getUserId());
		 
		 if(cartEntity!=null) {	
			 UserBean userBean = userService.getUserBean(cart.getUser().getUserId());
			 cart.setUser(userBean);
			 cartEntity= beanToEntity(cartEntity,cart,quantity);
			 beanToEntity(cartEntity,cart,quantity);
			 cartEntity = repo.save(cartEntity);	
			 entityToBean(cartEntity,cart);			 
		     log.info("Cart Service Implementation Save Method If True Part End  -> {}"+cart);
			 return new ResponseEntity<Cart>(cart, HttpStatus.OK);				 
		
		 }else {			 
			 CartEntity newCart = new CartEntity();
			 UserBean userBean = userService.getUserBean(cart.getUser().getUserId());
			 cart.setUser(userBean);
			 newCart.setUserId(userBean.getUserId());
			 newCart= beanToEntity(newCart,cart,quantity);
			 cartEntity = repo.save(newCart);	
			 cart = entityToBean(newCart,cart);
			 
			 log.info("Cart Service Implementation Save Method If Else End  -> {}"+cart);
			 return new ResponseEntity<Cart>(cart, HttpStatus.OK);
			 
		 }
		
	}

	public CartEntity beanToEntity(CartEntity cartEntity, Cart cart,int quantity) {
		
		log.info("Cart Service Implementation beanToEntity Method Start -> {}"+cart +cart.getProducts());
		
		cartEntity.setStatus(cart.getStatus());	
		List<ProductEntity > products=new ArrayList<>();
		if(cartEntity.getProducts()!=null) {
			products = cartEntity.getProducts();
		}
		while(true) {
			for(Product ele:cart.getProducts()) {
				
				ProductEntity obj = new ProductEntity();
				obj.setDescription(ele.getDescription());
				obj.setName(ele.getName());
				obj.setPrice(ele.getPrice());
				obj.setProductId(ele.getProductId());
				obj.setCategory(ele.getCategory());
				
				products.add(obj);	
				quantity--;
			}
			if(quantity==0) {
				break;
			}
		}
		
		cartEntity.setQuantity(products.size());	
		
		DoubleSummaryStatistics collect = products.stream()
				.collect(Collectors.summarizingDouble(ProductEntity::getPrice));		
		cartEntity.setAmount(collect.getSum());
		
		cartEntity.setProducts(products);
		
		log.info("Cart Service Implementation beanToEntity Method End -> {}"+cart);

		return cartEntity;
	}

	public Cart entityToBean(CartEntity cartEntity, Cart cart) {
	log.info("Cart Service Implementation entityToBean Method Start -> {}"+cart);

		
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
		
		log.info("Cart Service Implementation entityToBean Method End -> {}"+cart);
		
		return cart;
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
		
		log.info("Cart sevice implementation getCartById method start -> {}"+cartId);
		Optional<CartEntity> optional = repo.findById(cartId);
		if(optional.isPresent()) {
			ResponseStructure<Cart> structure = new ResponseStructure<>();
			Cart cart = new Cart();
			cart= entityToBean(optional.get(), cart);
			structure.setData(cart);
			structure.setMessage("Data found successfull based on this "+cartId);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<Cart>(cart, HttpStatus.FOUND);
		}
	 throw new CartIdNotFoundException("Cart Is Not Found By this Id "+cartId);
	}

	@Override
	public ResponseEntity<List<Product>> getProductsCartId(Integer cartId) {
		return null;
	}
	
	
	

}
