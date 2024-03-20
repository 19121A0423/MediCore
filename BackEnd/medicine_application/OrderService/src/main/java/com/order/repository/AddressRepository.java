package com.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.order.entity.Address;

import jakarta.transaction.Transactional;

@Transactional
public interface AddressRepository extends JpaRepository<Address, Integer>{
	
	@Modifying  //annotation is used to indicate that the method is modifying the database.
	@Query(value="UPDATE address SET status='inactive' where address_id=:id" , nativeQuery = true)
	void updateStatusById(@Param("id") int id);
	
	@Query(value="SELECT * FROM address WHERE user_id=:userId", nativeQuery = true)
	List<Address> getAddressByUserId(@Param("userId") int userId);

	
}
