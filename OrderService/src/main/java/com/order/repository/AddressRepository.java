package com.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.order.entity.AddressEntity;

import jakarta.transaction.Transactional;

@Transactional
public interface AddressRepository extends JpaRepository<AddressEntity, Integer>{
	
	@Modifying  //annotation is used to indicate that the method is modifying the database.
	@Query(value="UPDATE address SET status='inactive' where address_id=:id" , nativeQuery = true)
	void updateStatusById(@Param("id") int id);
	
}
