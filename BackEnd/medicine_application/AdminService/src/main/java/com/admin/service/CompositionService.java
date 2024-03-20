package com.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.admin.bean.CompositionBean;
import com.admin.entity.Composition;

@Service
public interface CompositionService {
   
	public CompositionBean insertComposition(CompositionBean composition);
	
	public List<Composition> getAllCompositions();
}