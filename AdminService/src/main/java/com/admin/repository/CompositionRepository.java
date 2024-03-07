package com.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.entity.Composition;

@Repository
public interface CompositionRepository extends JpaRepository<Composition, Integer>{

	
	
}
