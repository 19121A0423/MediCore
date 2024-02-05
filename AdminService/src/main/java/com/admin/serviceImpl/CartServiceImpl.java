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
import com.admin.exception.CartListNotFoundException;
import com.admin.exception.UserIdNotFoundException;
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
		
	@Autowired
	public UserServiceImpl userService;
	

	@Override
	public ResponseEntity<ResponseStructure<Cart>> save(Cart cart,int quantity) throws UserIdNotFoundException {
	
		log.info("Cart Service Implementation Save Method Start-> {}"+cart);
		
		if((cart.getUser()==null||cart.getProducts()==null)) {
			throw new IllegalArgumentException("User and Product properties cannot be null");
		}
		
		 UserBean userBean = userService.getUserBean(cart.getUser().getUserId());
		 
		 if(userBean==null) {
			 throw new UserIdNotFoundException("User Does Not Found By This Id "+cart.getUser().getUserId());
		 }
		 
		 CartEntity cartEntity = repo.getCartByUserId(cart.getUser().getUserId());
		 
		 if(cartEntity==null) {	
			 cartEntity = new CartEntity();			 			
		 } 
		 
		 cart.setUser(userBean);
		 cartEntity.setUserId(userBean.getUserId());
		 cartEntity= beanToEntity(cartEntity,cart,quantity);
		 cartEntity = repo.save(cartEntity);	
		 cart = entityToBean(cartEntity,cart);
		 
		 ResponseStructure<Cart> structure = new ResponseStructure<>();
		 structure.setData(cart);
		 structure.setMessage("Data Saved Successfully");
		 structure.setStatusCode(HttpStatus.CREATED.value());
				 
		 log.info("Cart Service Implementation Save Method  End  -> {}"+cart);
		 return new ResponseEntity<ResponseStructure<Cart>>(structure, HttpStatus.CREATED);
		 
		
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
	public ResponseEntity<ResponseStructure<Cart>> update(Cart cart,int quantity) throws CartIdNotFoundException{
		
		log.info("Cart service implementation update method {}"+cart);
		CartEntity cartEntity = repo.findById(cart.getCartId())
		.orElseThrow(()-> new CartIdNotFoundException("Cart Doesnot Exist By This Id"+cart.getCartId()));
		beanToEntity(cartEntity, cart, quantity);
		
		
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<Cart>> delete(Integer cartId) throws CartIdNotFoundException {
		
		log.info("Cart Service Implementation delete Method Start->");
		if(cartId==null){
			throw new IllegalArgumentException("Cart Id Cannot Be Empty");
		}		
		Optional<CartEntity> optional = repo.findById(cartId);
		if(optional.isPresent()){
			CartEntity cartEntity = optional.get();
			UserBean userBean = userService.getUserBean(cartEntity.getUserId());			
			cartEntity.setStatus("Deactivated");
			cartEntity.setProducts(null);
			cartEntity.setAmount(0.0);
			cartEntity.setQuantity(0);
			Cart bean = new Cart();
		    cartEntity = repo.save(cartEntity);
			bean.setAmount(0);
			bean.setUser(userBean);
			bean.setCartId(cartEntity.getCartId());
			bean.setProducts(null);
			bean.setStatus(cartEntity.getStatus());
			bean.setAmount(cartEntity.getAmount());
			
			ResponseStructure<Cart> structure = new ResponseStructure<>();
			structure.setData(bean);
			structure.setMessage("Cart Deleted SucessFully");
			structure.setStatusCode(HttpStatus.OK.value());
			log.info("Cart Service Implementation delete If True Method End->");
			return new ResponseEntity<ResponseStructure<Cart>>(structure, HttpStatus.OK);
		}else{
			log.info("Cart Service Implementation delete If else Method End->");
			throw new CartIdNotFoundException("Cart Not Found By This Id"+cartId);
		}	
	}


	@Override
	public ResponseEntity<ResponseStructure<List<Cart>>> getCartDetails() throws CartListNotFoundException{
		log.info("Cart sevice implementation getCartDetails method start -> ");
		List<CartEntity> cartList = repo.findAll();
		List<Cart> beanCartList = new ArrayList<>();
		
		
		if(!cartList.isEmpty()){		
			
			for(CartEntity cart : cartList){
				Cart bean = new Cart();
				
				UserBean userBean = userService.getUserBean(cart.getUserId());
				bean.setUser(userBean);
				bean = entityToBean(cart, bean);
				beanCartList.add(bean);
			}
			
		}else{
			throw new CartListNotFoundException("Cart Is Empty");
		}
		
		ResponseStructure<List<Cart>> structure = new ResponseStructure<>();
		structure.setData(beanCartList);
		structure.setMessage("Cart Details Found Successfully!!!");
		structure.setStatusCode(HttpStatus.FOUND.value());
		log.info("Cart sevice implementation getCartDetails method End -> ");
		return new ResponseEntity<ResponseStructure<List<Cart>>>(structure, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<ResponseStructure<Cart>>  getCartById(Integer cartId) throws CartIdNotFoundException {
		
		log.info("Cart sevice implementation getCartById method start -> {}"+cartId);
		if(cartId==null){
			throw new IllegalArgumentException("Cart Id Cannot Be Empty");
		}		
		Optional<CartEntity> optional = repo.findById(cartId);
		if(optional.isPresent()) {
			
			
			ResponseStructure<Cart> structure = new ResponseStructure<>();
			Cart cart = new Cart();
			UserBean userBean = userService.getUserBean(optional.get().getUserId());
			cart.setUser(userBean);
			cart= entityToBean(optional.get(), cart);
			structure.setData(cart);
			structure.setMessage("Data found successfull based on this "+cartId);
			structure.setStatusCode(HttpStatus.FOUND.value());
			log.info("Cart sevice implementation getCartById method If Condition End -> {}"+cartId);			
			return new ResponseEntity<ResponseStructure<Cart>>(structure, HttpStatus.FOUND);
		}
		log.info("Cart sevice implementation getCartById method  End -> {}"+cartId);				
	 	throw new CartIdNotFoundException("Cart Is Not Found By this Id "+cartId);
	}

	
	
}