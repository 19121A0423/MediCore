package com.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.bean.Composition;
import com.admin.entity.CompositionEntity;
import com.admin.service.CompositionService;

@RestController
@RequestMapping("/composition")
public class CompositionController {

	private static Logger log = LoggerFactory
			.getLogger(ProductController.class.getSimpleName());
	
	@Autowired
	private CompositionService compositionService;
	
	
	@PostMapping("/insert")
	public ResponseEntity<Composition> insert(@RequestBody Composition composition){
		log.info("Start CompositionController::insert()");
		compositionService.insert(composition);
		log.info("End CompositionController::insert()");
		return new ResponseEntity<Composition>(composition,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<CompositionEntity>> getAll(){
		log.info("Start CompositionController::getAll()");
		List<CompositionEntity> compositionEntities=compositionService.getAll();
		log.info("End CompositionController::getAll()");
		return new ResponseEntity<List<CompositionEntity>>(compositionEntities,HttpStatus.OK);
	}
}
