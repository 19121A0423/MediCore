package com.admin.productService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.bean.Category;
import com.admin.bean.Product;
import com.admin.entity.CategoryEntity;
import com.admin.entity.ProductEntity;
import com.admin.exception.ProductNotFoundException;
import com.admin.repository.ProductRepository;

@Service
public class ProductServiceImplementation implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void insert(Product product) {
		ProductEntity entity=new ProductEntity();
		entity.setProductId(product.getProductId());
		entity.setName(product.getName());
		entity.setPrice(product.getPrice());
		entity.setQuantity(product.getQuantity());
		entity.setDescription(product.getDescription());
		CategoryEntity categoryEntity=new CategoryEntity();
		categoryEntity.setCategoryId(product.getCategory().getCategoryId());
		entity.setCategory(categoryEntity);
		productRepository.save(entity);
	
	}

	@Override
	public ProductEntity get(Integer productId) {
	    return productRepository.findById(productId).get();
	}

	@Override
	public List<Product> getAll() {
		List<ProductEntity> productEntities=productRepository.findAll();
		List<Product> products = convert(productEntities);
		return products;
	}
	
public 	List<Product> convert(List<ProductEntity> productEntities) {
		
		List<Product> products = new ArrayList<>();
		
		for(ProductEntity entity:productEntities) {
			Product product=new Product();
			product.setProductId(entity.getProductId());	
			product.setName(entity.getName());	
			product.setPrice(entity.getPrice());	
			product.setQuantity(entity.getQuantity());	
			product.setDescription(entity.getDescription());
			
			CategoryEntity category = new CategoryEntity();
	        category.setCategoryId(entity.getCategory().getCategoryId());
	        category.setCategoryName(entity.getCategory().getCategoryName());

	        product.setCategory(category);
			
			products.add(product);
		}
		return products;
		
		
		
	}

	@Override
	public void update(Integer productId, ProductEntity entity) {
	Optional<ProductEntity> productOptional=productRepository.findById(productId);
		if(productOptional.isPresent()) {
		ProductEntity productEntity =productOptional.get();
			productRepository.save(entity);
		}
		else {
			try {
			throw new ProductNotFoundException("Product not found");
			}
			catch(ProductNotFoundException exception) {
				exception.getMessage();
			}
		}
	}

	@Override
	public ProductEntity delete(Integer productId) {
		Optional<ProductEntity> productOptional=productRepository.findById(productId);
		if(productOptional.isPresent()) {
		ProductEntity productEntity =productOptional.get();
			productRepository.delete(productEntity);
			
			return productEntity;
		}
		else {
			try {
				throw new ProductNotFoundException("Product not found");
				}
				catch(ProductNotFoundException exception) {
					exception.getMessage();
				}
			return null;
		}
		
	}

}
