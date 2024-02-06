package com.ooms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ooms.bean.UserBean;
import com.ooms.exception.UserNotFoundByIdException;
import com.ooms.service.UserService;
import com.ooms.structure.ResponseStructure;

@RestController
public class UserController {
	
	private static Logger log = LoggerFactory
			.getLogger(UserController.class.getSimpleName());

	@Autowired
	private UserService service;

	@PostMapping("/users/save")
	public ResponseEntity<ResponseStructure<UserBean>> save(@RequestBody UserBean user) {
		log.info("UserController save method start {}"+user);	
		ResponseEntity<ResponseStructure<UserBean>> response=null;
		try {
			response= service.save(user);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		log.info("UserController save method end{}"+user);	
		return response;
	}

	@PutMapping("/users/update")
	public ResponseEntity<ResponseStructure<UserBean>> update(@RequestBody UserBean user) throws UserNotFoundByIdException {
		log.info("UserController update method start {}"+user);	
		ResponseEntity<ResponseStructure<UserBean>> response=null;
		try {
	    response =service.update(user);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		log.info("UserController update method 	end {}"+user);	
		return response;
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserBean>> getById(@PathVariable int userId) throws UserNotFoundByIdException {
		
		log.info("UserController getById method start {}"+userId);	
		ResponseEntity<ResponseStructure<UserBean>> response=null;
		try {
			response =service.getById(userId);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		log.info("UserController getById method 	end {}"+userId);	
		
		return response;
	}

	@DeleteMapping("/users/delete/{userId}")
	public ResponseEntity<ResponseStructure<UserBean>> delete(@PathVariable int userId) throws UserNotFoundByIdException {
		log.info("UserController delete method start {}"+userId);	
		ResponseEntity<ResponseStructure<UserBean>> response=null;
		try {
			response =service.delete(userId);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		log.info("UserController delete method 	end {}"+userId);			
		return response;
	}

	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<UserBean>>> getAll() {
		log.info("UserController getAll method start");	
		ResponseEntity<ResponseStructure<List<UserBean>>> response = service.getAll();
		log.info("UserController getAll method end");	
		return response;
	}

}
