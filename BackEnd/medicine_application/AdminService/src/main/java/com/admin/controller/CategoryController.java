package com.admin.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("*")
public class CategoryController {

	private static Logger log = LoggerFactory.getLogger(ProductController.class.getSimpleName());

	@Autowired
	private CategoryService categoryService;

	/**
	 * Inserts a new category.
	 * 
	 * @param category The category to be inserted.
	 * @return The inserted category.
	 */
	@PostMapping("/insertcategory")
	public ResponseEntity<CategoryBean> saveCategory(@RequestBody CategoryBean category) {
		categoryService.insertCategory(category);
		log.info("Category details inserting {}", category);
		ResponseEntity<CategoryBean> entity = new ResponseEntity<CategoryBean>(category, HttpStatus.CREATED);
		return entity;
	}

	/**
	 * Retrieves a category by its ID.
	 * 
	 * @param categoryId The ID of the category to retrieve.
	 * @return The category with the specified ID.
	 */
	@GetMapping("/getbycategoryid/{categoryId}")
	public ResponseEntity<Category> getByCategoryId(@PathVariable Integer categoryId) {
		Category category = categoryService.getByCategoryId(categoryId);
		log.info("Category details{}", category);
		ResponseEntity<Category> entity = new ResponseEntity<Category>(category, HttpStatus.OK);
		return entity;
	}

	/**
	 * Retrieves all categories.
	 * 
	 * @return A list of all categories.
	 */
	@GetMapping("/getallcategories")
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = categoryService.getAllCategories();
		log.info("Category details{}", categories);
		ResponseEntity<List<Category>> entity = new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
		return entity;
	}

	/**
	 * Deletes a category by its ID.
	 * 
	 * @param categoryId The ID of the category to delete.
	 * @return A message indicating successful deletion.
	 */
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<String> deleteById(@PathVariable Integer categoryId) {

		try {
			categoryService.deleteCategory(categoryId);
			return new ResponseEntity<String>("Category record deleted successfully ", HttpStatus.OK);
		} catch (CategoryNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * Updates a category by its ID.
	 * 
	 * @param categoryEntity The updated category information.
	 * @param categoryId     The ID of the category to be updated.
	 * @return A message indicating successful update.
	 */
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<String> updateCategoryById(@RequestBody Category categoryEntity,
			@PathVariable Integer categoryId) {
		try {
			categoryService.updateCategory(categoryId, categoryEntity);
			log.info("Category details{}", categoryEntity);
			return new ResponseEntity<String>("Category record updated successfully ", HttpStatus.OK);
		} catch (CategoryNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
