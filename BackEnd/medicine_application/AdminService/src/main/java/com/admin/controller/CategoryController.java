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

import com.admin.bean.CategoryBean;
import com.admin.entity.Category;
import com.admin.exception.CategoryNotFoundException;
import com.admin.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	private static Logger log = LoggerFactory
			.getLogger(ProductController.class.getSimpleName());
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/insertcategory")
	public ResponseEntity<CategoryBean> saveCategory(@RequestBody CategoryBean category){
		categoryService.insertCategory(category);
		log.info("Category details inserting {}",category);
		ResponseEntity<CategoryBean> entity=new ResponseEntity<CategoryBean>
		(category,HttpStatus.CREATED);
	    return entity;	
	}
	
	@GetMapping("/getbycategoryid/{categoryId}")
	public ResponseEntity<Category> getByCategoryId(@PathVariable Integer categoryId){
		Category category=categoryService.getByCategoryId(categoryId);
		log.info("Category details{}",category);
		ResponseEntity<Category> entity=new ResponseEntity<Category>
		(category,HttpStatus.OK);
		return entity;
	}
	
	@GetMapping("/getallcategories")
	public ResponseEntity<List<Category>> getAllCategories(){
		List<Category> categories=categoryService.getAllCategories();
		log.info("Category details{}",categories);
		ResponseEntity<List<Category>> entity=new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
		return entity;
	}
	
	
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<String> deleteById(@PathVariable Integer categoryId){
	
	try {
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<String>
		("Category record deleted successfuly ",HttpStatus.OK); 
	} catch (CategoryNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
		
	}
	
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<String> updateCategoryById(@RequestBody Category categoryEntity,
			@PathVariable Integer categoryId) {
	        try {
				categoryService.updateCategory(categoryId, categoryEntity);
				log.info("Category details{}",categoryEntity);
				return new ResponseEntity<String>("category record updated successfilly ",HttpStatus.OK);
			} catch (CategoryNotFoundException e) {
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
			}
			
			
		}
}