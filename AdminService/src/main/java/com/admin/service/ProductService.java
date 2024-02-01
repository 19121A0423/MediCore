package com.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.admin.bean.Product;
import com.admin.entity.ProductEntity;
import com.admin.exception.ProductNotFoundException;

@Service
public interface ProductService {

	public void insert(Product product) throws ProductNotFoundException;
	
	public ProductEntity get(Integer productId);
	
    public List<Product> getAll();
	
	public void update(Integer productId, Product product) throws ProductNotFoundException;
	
	public void delete(Integer productId) throws ProductNotFoundException;
//	public void delete(Integer productId);


	List<Product> searchProductByCategory(Optional<Integer> categoryId);
	
}
