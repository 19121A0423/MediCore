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
import com.admin.productService.ProductService;

@RestController
@RequestMapping("/productController")
public class ProductController {

	private static Logger log = LoggerFactory
			.getLogger(ProductController.class.getSimpleName());
	
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<Product> save(@RequestBody Product product)
	{
		productService.insert(product);
		log.info("Products{}",product);
		ResponseEntity<Product> entity=new ResponseEntity<Product>
		(product,HttpStatus.CREATED);
		
		return entity;
		
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
	public ResponseEntity<String> update(@RequestBody ProductEntity entity,
			@PathVariable Integer productId) {
		try {
			productService.update(productId, entity);
			log.info("Updated Product details{}", entity);
			return new ResponseEntity<String>("Product details updated",HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<String> delete(@PathVariable Integer productId){
		try {
			productService.delete(productId);
			return new ResponseEntity<String>("Product successfully deleted",HttpStatus.ACCEPTED);
		} catch (ProductNotFoundException e) {
		   return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/searchByCategory/{categoryId}")
	public ResponseEntity<List<Product>> searchByCategory(@PathVariable Optional<Integer> categoryId){
		List<Product> products=productService.searchProductByCategory(categoryId);
		log.info("Product Details{}",products);
		ResponseEntity<List<Product>> entity=new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		return entity;
		
	}
}
