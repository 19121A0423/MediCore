package com.admin.productService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.admin.bean.Product;
import com.admin.entity.ProductEntity;
import com.admin.exception.ProductNotFoundException;

@Service
public interface ProductService {

	public void insert(Product product);
	
	public ProductEntity get(Integer productId);
	
    public List<Product> getAll();
	
	public void update(Integer productId, ProductEntity entity) throws ProductNotFoundException;
	
	public void delete(Integer productId) throws ProductNotFoundException;


	List<Product> searchProductByCategory(Optional<Integer> categoryId);
	
}
