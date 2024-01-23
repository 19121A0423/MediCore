package com.ooms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ooms.entity.bean.UserBean;
import com.ooms.service.UserService;
import com.ooms.util.ResponseStructure;

@RestController
public class UserController {
	
	
	@Autowired
	private UserService service;
	
	
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<UserBean>> save(@RequestBody UserBean user) {	
		return service.save(user);		
	}
	
	@PutMapping("/users")
	public ResponseEntity<ResponseStructure<UserBean>> update(@RequestBody UserBean user) {
		return service.update(user);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserBean>> getById(@PathVariable int userId) {
		return service.getById(userId);
	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserBean>> delete(@PathVariable int userId) {
		return service.delete(userId);
	}
	
	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<UserBean>>> getAll() {
		return service.getAll();
	}
	
}
