package com.admin.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.bean.Product;
import com.admin.entity.ProductEntity;
import com.admin.exception.ProductNotFoundException;
import com.admin.service.ProductService;

@RestController
@RequestMapping("/productController")
public class ProductController {

	private static Logger log = LoggerFactory
			.getLogger(ProductController.class.getSimpleName());
	
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<String> save(@RequestBody Product product)
	{
		log.info("Start Product Controller:insert()");
		try {
			productService.insert(product);
			log.info("Products{}",product);
			return new ResponseEntity<String>("Inserted successfully"+product,HttpStatus.CREATED);
		} catch (ProductNotFoundException e) {
			log.info("Handling Exception in ProductControl::insert() ");
			log.info("End Product Controller:insert()");
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductEntity> getById(@PathVariable Integer productId){
		ProductEntity product=productService.get(productId);
		log.info("Product Details of id{}",product);
		ResponseEntity<ProductEntity> entity=new ResponseEntity<ProductEntity>(product,HttpStatus.OK);
		return entity;
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Product>> getAll(){
		List<Product> products=productService.getAll();
		log.info("Product Details {}", products);
		ResponseEntity<List<Product>>entity=new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		return entity;
	}
	
	@PutMapping("/update/{productId}")
	public ResponseEntity<String> update(@RequestBody Product product,
			@PathVariable Integer productId) {
		log.info("Start Product Controller:update()");
		try {
			productService.update(productId, product);
			return new ResponseEntity<String>("Product details updated"+product,HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			log.info("Handling Exception in ProductControl::update() ");
			log.info("End Product Controller:update()");
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<String> delete(@PathVariable Integer productId){
		log.info("Start Product Controller:delete()");
			try {
				productService.delete(productId);
			return new ResponseEntity<String>("Deleting the product with Id "+productId,HttpStatus.ACCEPTED);
			
			} catch (ProductNotFoundException e) {
				log.info("Handling Exception in ProductControl::delete() ");
				log.info("End Product Controller:delete()");
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
			}
		
	}
	
	@GetMapping("/searchByCategory/{categoryId}")
	public ResponseEntity<List<Product>> searchByCategory(@PathVariable Optional<Integer> categoryId){
		log.info(" Start::Product controller:searchByCategory::categoryId"+categoryId);
		List<Product> products=productService.searchProductByCategory(categoryId);
		ResponseEntity<List<Product>> entity=new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		log.info(" End::Product controller:searchByCategory::Product details"+products);

		return entity;
		
	}
}
