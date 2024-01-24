package com.admin.categoryService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.bean.Category;
import com.admin.entity.CategoryEntity;
import com.admin.exception.CategoryNotFoundException;
import com.admin.repository.CategoryRepository;

@Service
public class CategoryServiceImplementation implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public void insert(Category category) {
		CategoryEntity categoryEntity=new CategoryEntity();
		categoryEntity.setCategoryName(category.getCategoryName());
     	 categoryRepository.save(categoryEntity);
	}

	@Override
	public CategoryEntity getById(Integer categoryId) {
		return categoryRepository.findById(categoryId).get();
	}

	@Override
	public List<Category> getAll() {
	
		List<CategoryEntity> categoryEntities=categoryRepository.findAll();
		List<Category> categories = convert(categoryEntities);
		return categories;
	}

	public 	List<Category> convert(List<CategoryEntity> categoryEntities) {
		
		List<Category> categories = new ArrayList<>();
		
		for(CategoryEntity entity:categoryEntities) {
			Category category=new Category();
			category.setCategoryId(entity.getCategoryId());
			category.setCategoryName(entity.getCategoryName());
			categories.add(category);
		}
		return categories;
		
		
		
	}
	
	
	@Override
	public void update(Integer categoryId, CategoryEntity entity) {
		CategoryEntity categoryEntity=categoryRepository.findById(categoryId).get();
		if(categoryEntity!=null) {
			categoryRepository.save(entity);
		}
		else {
			try {
			 throw new CategoryNotFoundException();
			}
			catch(CategoryNotFoundException exception) {
				  exception.getMessage();
			}
		}
		
		
	}

	@Override
	public CategoryEntity delete(Integer categoryId) {
	CategoryEntity categoryEntity=	categoryRepository.findById(categoryId).get();
	if(categoryEntity!=null) {
     categoryRepository.deleteById(categoryId);
     
	}
	else {
		try {
			 throw new CategoryNotFoundException();
			}
			catch(CategoryNotFoundException exception) {
				  exception.getMessage();
			}
	}
	return categoryEntity;
	}

}
