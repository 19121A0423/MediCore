package com.admin.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.bean.CategoryBean;
import com.admin.entity.Category;
import com.admin.exception.CategoryNotFoundException;
import com.admin.repository.CategoryRepository;
import com.admin.service.CategoryService;

@Service
public class CategoryServiceImplementation implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    
    /**
     * Inserts a new category into the database.
     * 
     * @param category The category to be inserted.
     */
    @Override
    public void insertCategory(CategoryBean category) {
        Category categoryEntity = new Category();
        categoryEntity.setCategoryName(category.getCategoryName());
        categoryRepository.save(categoryEntity);
    }

    /**
     * Retrieves a category by its ID from the database.
     * 
     * @param categoryId The ID of the category to retrieve.
     * @return The category with the specified ID.
     * @throws CategoryNotFoundException if no category with the specified ID is found.
     */
    @Override
    public Category getByCategoryId(Integer categoryId) throws CategoryNotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        } else {
            throw new CategoryNotFoundException("Category not found with Id- " + categoryId);
        }
    }

    /**
     * Retrieves all categories from the database.
     * 
     * @return A list of all categories.
     */
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    /**
     * Updates a category in the database.
     * 
     * @param categoryId The ID of the category to update.
     * @param entity The updated category entity.
     * @throws CategoryNotFoundException if no category with the specified ID is found.
     */
    @Override
    public void updateCategory(Integer categoryId, Category entity) throws CategoryNotFoundException {
        Optional<Category> optional = categoryRepository.findById(categoryId);
        if (optional.isPresent()) {
            categoryRepository.save(entity);
        } else {
            throw new CategoryNotFoundException("Category not found with Id- " + categoryId);
        }
    }

    /**
     * Deletes a category from the database by its ID.
     * 
     * @param categoryId The ID of the category to delete.
     * @throws CategoryNotFoundException if no category with the specified ID is found.
     */
    @Override
    public void deleteCategory(Integer categoryId) throws CategoryNotFoundException {
        Optional<Category> optional = categoryRepository.findById(categoryId);
        if (optional.isPresent()) {
            categoryRepository.deleteById(categoryId);
        } else {
            throw new CategoryNotFoundException("Category not found with Id- " + categoryId);
        }
    }
}
