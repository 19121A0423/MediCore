package com.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.admin.bean.CategoryBean;
import com.admin.entity.Category;
import com.admin.exception.CategoryNotFoundException;


@Service
public interface CategoryService {

	public void  insertCategory(CategoryBean category);
	
	public Category  getByCategoryId(Integer categoryId);
	
	public List<Category> getAllCategories();
	
	public void updateCategory(Integer categoryId, Category entity) throws CategoryNotFoundException;
	
	public void deleteCategory(Integer categoryId) throws CategoryNotFoundException;
}
