package com.admin.categoryService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.admin.bean.Category;
import com.admin.entity.CategoryEntity;
import com.admin.exception.CategoryNotFoundException;


@Service
public interface CategoryService {

	public void  insert(Category category);
	
	public CategoryEntity  getById(Integer categoryId);
	
	public List<Category> getAll();
	
	public void update(Integer categoryId, CategoryEntity entity) throws CategoryNotFoundException;
	
	public CategoryEntity delete(Integer categoryId) throws CategoryNotFoundException;
}
