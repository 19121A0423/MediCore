package com.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.admin.bean.Composition;
import com.admin.entity.CompositionEntity;

@Service
public interface CompositionService {
   
	public void insert(Composition composition);
	
	public List<CompositionEntity> getAll();
}
