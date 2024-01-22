package com.admin.productService;

import java.util.List;

import org.springframework.stereotype.Service;


import com.admin.bean.Product;
import com.admin.entity.ProductEntity;

@Service
public interface ProductService {

	public void insert(Product product);
	
	public ProductEntity get(Integer productId);
	
    public List<Product> getAll();
	
	public void update(Integer productId, ProductEntity entity);
	
	public ProductEntity delete(Integer productId);
	
}
