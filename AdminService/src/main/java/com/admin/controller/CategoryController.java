package com.admin.controller;

import java.util.List;

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

import com.admin.bean.Category;
import com.admin.entity.CategoryEntity;
import com.admin.exception.CategoryNotFoundException;
import com.admin.service.CategoryService;

@RestController
@RequestMapping("/categoryController")
public class CategoryController {

	private static Logger log = LoggerFactory
			.getLogger(ProductController.class.getSimpleName());
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<Category> save(@RequestBody Category category){
		categoryService.insert(category);
		log.info("Category details inserting {}",category);
		ResponseEntity<Category> entity=new ResponseEntity<Category>
		(category,HttpStatus.CREATED);
	    return entity;	
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryEntity> getById(@PathVariable Integer categoryId){
		CategoryEntity category=categoryService.getById(categoryId);
		log.info("Category details{}",category);
		ResponseEntity<CategoryEntity> entity=new ResponseEntity<CategoryEntity>
		(category,HttpStatus.OK);
		return entity;
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<CategoryEntity>> getAll(){
		List<CategoryEntity> categories=categoryService.getAll();
		log.info("Category details{}",categories);
		ResponseEntity<List<CategoryEntity>> entity=new ResponseEntity<List<CategoryEntity>>(categories,HttpStatus.OK);
		return entity;
	}
	
	
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<String> deleteById(@PathVariable Integer categoryId){
	
	try {
		categoryService.delete(categoryId);
		return new ResponseEntity<String>
		("Category record deleted successfuly ",HttpStatus.OK); 
	} catch (CategoryNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
		
	}
	
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<String> updateById(@RequestBody CategoryEntity categoryEntity,
			@PathVariable Integer categoryId) {
	        try {
				categoryService.update(categoryId, categoryEntity);
				log.info("Category details{}",categoryEntity);
				return new ResponseEntity<String>("category record updated successfilly ",HttpStatus.OK);
			} catch (CategoryNotFoundException e) {
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
			}
			
			
		}
}
