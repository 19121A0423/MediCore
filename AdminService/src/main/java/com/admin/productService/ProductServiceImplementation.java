package com.admin.productService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.bean.Product;
import com.admin.entity.ProductEntity;
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
		entity.setCategoryId(product.getCategoryId());
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
			product.setCategoryId(entity.getCategoryId());	
			products.add(product);
		}
		return products;
		
		
		
	}

	@Override
	public void update(Integer productId, ProductEntity entity) {
		ProductEntity  productEntity=productRepository.findById(productId).get();
		if(productEntity!=null) {
			productRepository.save(entity);
		}
		else {
			System.out.println("Id is not present");
		}
	}

	@Override
	public ProductEntity delete(Integer productId) {
		ProductEntity  productEntity=productRepository.findById(productId).get();
		if(productEntity!=null) {
			productRepository.delete(productEntity);;
		}
		else {
			System.out.println("Id is not present");
		}
		return null;
	}

}
