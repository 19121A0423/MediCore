package com.admin.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.bean.Category;
import com.admin.entity.CategoryEntity;
import com.admin.exception.CategoryNotFoundException;
import com.admin.repository.CategoryRepository;
import com.admin.service.CategoryService;

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
	public void update(Integer categoryId, CategoryEntity entity) throws CategoryNotFoundException {
		Optional<CategoryEntity> optional =categoryRepository.findById(categoryId);
		if(optional.isPresent()) {
		CategoryEntity categoryEntity=optional.get();	
	     categoryRepository.save(entity);
		}
		else {
			 throw new CategoryNotFoundException("Category not found with Id- "+categoryId);
			}
		}
		
		
	

	@Override
	public void delete(Integer categoryId) throws CategoryNotFoundException {
	Optional<CategoryEntity> optional =categoryRepository.findById(categoryId);
	if(optional.isPresent()) {
	CategoryEntity categoryEntity=optional.get();	
     categoryRepository.deleteById(categoryId);
     
	}
	else {
		 throw new CategoryNotFoundException("Category not found with Id- "+categoryId);
		
	}
	}

}
