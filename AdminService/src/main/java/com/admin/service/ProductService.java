package com.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.admin.bean.CompositionBean;
import com.admin.bean.ProductBean;
import com.admin.entity.Product;
import com.admin.exception.CategoryNotFoundException;
import com.admin.exception.ProductNotFoundException;

@Service
public interface ProductService {

	public ProductBean insertProduct(ProductBean product);
	
	public ProductBean getProductById(Integer productId);
	
	public List<ProductBean> getProductsByCategoryName(String categoryName) throws CategoryNotFoundException;
	
    public List<ProductBean> getAll();
	
	public void update(Integer productId, ProductBean product) throws ProductNotFoundException;
	
	public void delete(Integer productId) throws ProductNotFoundException;

	public List<ProductBean> searchProductByCategory(Optional<Integer> categoryId);
		
	List<ProductBean> searchSimilarProducts(String productName) throws ProductNotFoundException;
	
	  public List<CompositionBean> getCompositionsByProductId(Integer productId);
}
