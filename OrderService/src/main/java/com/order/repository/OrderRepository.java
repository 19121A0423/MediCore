package com.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.order.entity.OrderEntity;

@Transactional
public interface OrderRepository extends JpaRepository<OrderEntity, Integer>{

	@Modifying  //annotation is used to indicate that the method is modifying the database.
	@Query(value="UPDATE orders SET status='delivered' where order_id=:id" , nativeQuery = true)
	void updateStatusById(@Param("id") int id);
	
}
