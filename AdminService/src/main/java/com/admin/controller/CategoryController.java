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
import com.admin.categoryService.CategoryService;
import com.admin.entity.CategoryEntity;
import com.admin.exception.CategoryNotFoundException;

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
	public ResponseEntity<List<Category>> getAll(){
		List<Category> categories=categoryService.getAll();
		log.info("Category details{}",categories);
		ResponseEntity<List<Category>> entity=new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
		return entity;
	}
	
	
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<CategoryEntity> deleteById(@PathVariable Integer categoryId) throws CategoryNotFoundException{
	CategoryEntity category=categoryService.delete(categoryId);
		log.info("Category details{}",category);
		ResponseEntity<CategoryEntity> entity=new ResponseEntity<CategoryEntity>
		(category,HttpStatus.OK); 
		return entity;
	}
	
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<CategoryEntity> updateById(@RequestBody CategoryEntity categoryEntity, @PathVariable Integer categoryId) throws CategoryNotFoundException{
	        categoryService.update(categoryId, categoryEntity);
			log.info("Category details{}",categoryEntity);
			ResponseEntity<CategoryEntity> entity=new ResponseEntity<CategoryEntity>
			(categoryEntity,HttpStatus.OK);
			return entity;
		}
}
