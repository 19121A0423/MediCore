package com.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.order.entity.FeedbackEntity;

@Transactional
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Integer>{
	
}
