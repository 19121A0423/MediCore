package com.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.admin.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{
	
@Query(value="select * from product p where p.category_id= :categoryId", nativeQuery = true)
 public List<ProductEntity> findByCategory(Optional<Integer> categoryId);

@Query(value="SELECT p.* FROM product p JOIN product_category c ON p.category_id = c.category_id WHERE c.category_name = :categoryName", nativeQuery = true)
public List<ProductEntity> findByCategoryName(String categoryName);
}
