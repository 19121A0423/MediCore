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
    
    /**
     * Inserts a new composition into the database.
     * 
     * @param composition The composition to be inserted.
     * @return The inserted composition.
     */
    @Override
    public CompositionBean insertComposition(CompositionBean composition) {
        Composition compositionEntity = new Composition();
        compositionEntity.setCompositionName(composition.getCompositionName());
        compositionRepository.save(compositionEntity);
        return composition;
    }

    /**
     * Retrieves all compositions from the database.
     * 
     * @return A list of all compositions.
     */
    @Override
    public List<Composition> getAllCompositions() {
        List<Composition> compositionEntities = compositionRepository.findAll();
        return compositionEntities;
    }
}
