package com.admin.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.admin.bean.CompositionBean;
import com.admin.controller.CompositionController;
import com.admin.entity.Composition;
import com.admin.service.CompositionService;

@ExtendWith(MockitoExtension.class)
public class CompositionControllerTest {

    @Mock
    private CompositionService compositionService;
    
    @InjectMocks
    private CompositionController compositionController;
    
    private CompositionBean composition;

    @BeforeEach
    public void setUp() {
        composition = new CompositionBean();
        composition.setCompositionId(1);
        composition.setCompositionName("Pain Killer");
    }

    @Test
    public void testInsert() {
    	when(compositionService.insertComposition(any(CompositionBean.class))).thenReturn(composition);
        
        ResponseEntity<CompositionBean> response = compositionController.insertComposition(composition);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(composition, response.getBody());
    }

    
    @Test
    public void testGetAll() {
        List<Composition> compositionEntities = new ArrayList<>();
        Composition compositionEntity = new Composition();
        compositionEntity.setCompositionId(1);
        compositionEntity.setCompositionName("Pain Killer");
        compositionEntities.add(compositionEntity);

        when(compositionService.getAllCompositions()).thenReturn(compositionEntities);

        ResponseEntity<List<Composition>> response = compositionController.getAllCompositions();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(compositionEntities, response.getBody());
    }
  
}
