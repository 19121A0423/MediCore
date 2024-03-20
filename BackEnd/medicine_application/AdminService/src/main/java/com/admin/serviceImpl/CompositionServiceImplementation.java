package com.admin.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.bean.CompositionBean;
import com.admin.entity.Composition;
import com.admin.repository.CompositionRepository;
import com.admin.service.CompositionService;


@Service
public class CompositionServiceImplementation implements CompositionService{

	@Autowired
	private CompositionRepository compositionRepository;
	
	@Override
	public CompositionBean insertComposition(CompositionBean composition) {
		Composition compositionEntity=new Composition();
		compositionEntity.setCompositionName(composition.getCompositionName());
		compositionRepository.save(compositionEntity);
		return composition;
	}

	@Override
	public List<Composition> getAllCompositions() {
		List<Composition> compositionEntities=compositionRepository.findAll();
		return compositionEntities;
	}

	
}