package com.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.bean.CompositionBean;
import com.admin.entity.Composition;
import com.admin.service.CompositionService;

@RestController
@RequestMapping("/compositions")
@CrossOrigin("*")
public class CompositionController {

    private static Logger log = LoggerFactory.getLogger(CompositionController.class.getSimpleName());

    @Autowired
    private CompositionService compositionService;

    /**
     * Inserts a new composition.
     * 
     * @param composition The composition to be inserted.
     * @return The inserted composition.
     */
    @PostMapping("/insert")
    public ResponseEntity<CompositionBean> insertComposition(@RequestBody CompositionBean composition) {
        log.info("Start CompositionController::insert()");
        CompositionBean composition2 = compositionService.insertComposition(composition);
        log.info("End CompositionController::insert()");
        return new ResponseEntity<CompositionBean>(composition2, HttpStatus.CREATED);
    }

    /**
     * Retrieves all compositions.
     * 
     * @return A list of all compositions.
     */
    @GetMapping("/getcomposition")
    public ResponseEntity<List<Composition>> getAllCompositions() {
        log.info("Start CompositionController::getAll()");
        List<Composition> compositionEntities = compositionService.getAllCompositions();
        log.info("End CompositionController::getAll()");
        return new ResponseEntity<List<Composition>>(compositionEntities, HttpStatus.OK);
    }
}
