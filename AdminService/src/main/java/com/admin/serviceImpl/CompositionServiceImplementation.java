package com.admin.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.bean.Composition;
import com.admin.entity.CompositionEntity;
import com.admin.repository.CompositionRepository;
import com.admin.service.CompositionService;


@Service
public class CompositionServiceImplementation implements CompositionService{

	@Autowired
	private CompositionRepository compositionRepository;
	
	@Override
	public void insert(Composition composition) {
		CompositionEntity compositionEntity=new CompositionEntity();
		compositionEntity.setCompositionName(composition.getCompositionName());
		compositionRepository.save(compositionEntity);
	}

	@Override
	public List<CompositionEntity> getAll() {
		List<CompositionEntity> compositionEntities=compositionRepository.findAll();
		return compositionEntities;
	}

	
}
