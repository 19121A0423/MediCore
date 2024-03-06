package com.user.controller;

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

import com.user.bean.PasswordUpdateRequest;
import com.user.bean.User;
import com.user.exception.DuplicateMobileNumberException;
import com.user.exception.DuplicateEmailIdException;
import com.user.exception.UserNotFoundByIdException;
import com.user.service.UserService;

@RestController
public class UserController {
	
	private static Logger log = LoggerFactory
			.getLogger(UserController.class.getSimpleName());

	@Autowired
	private UserService service;

	@PostMapping("/users/save")
	public ResponseEntity<User> saveUserDetails(@RequestBody User user) throws DuplicateEmailIdException, DuplicateMobileNumberException {
		log.info("UserController save method start {}"+user);	
		User userBean=null;
		try {
			 userBean = service.saveUserDetails(user);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		log.info("UserController save method end{}"+user);	
		 return ResponseEntity.status(HttpStatus.OK).body(userBean);
	}

	@PutMapping("/users/update")
	public ResponseEntity<User> updateUserDetails(@RequestBody User user) throws UserNotFoundByIdException {
		log.info("UserController update method start {}"+user);	
		User userBean=null;
		try {
			 userBean = service.updateUserDetails(user);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		log.info("UserController update method 	end {}"+user);	
		 return ResponseEntity.status(HttpStatus.OK).body(userBean);
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUserDetailsByUserId(@PathVariable int userId) throws UserNotFoundByIdException {
		
		log.info("UserController getById method start {}"+userId);	
		User userBean=null;
		try {
			 userBean = service.getUserDetailsByUserId(userId);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		log.info("UserController getById method 	end {}"+userId);	
		
		return ResponseEntity.status(HttpStatus.OK).body(userBean);
	}

	@DeleteMapping("/users/delete/{userId}")
	public ResponseEntity<User> deleteUserDetailsByUserId(@PathVariable int userId) throws UserNotFoundByIdException {
		log.info("UserController delete method start {}"+userId);	
		User userBean=null;
		try {
			  userBean = service.deleteUserDetailsByUserId(userId);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		log.info("UserController delete method 	end {}"+userId);			
		return ResponseEntity.status(HttpStatus.OK).body(userBean);
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUserDetails() {
		log.info("UserController getAll method start");	
		 List<User> usersList = service.getAllUserDetails();
		log.info("UserController getAll method end");	
		return ResponseEntity.status(HttpStatus.OK).body(usersList);
	}

	
	@GetMapping("/users/validate/{userEmail}/{userPassword}")
	public ResponseEntity<User> userValiadtion(@PathVariable String userEmail, @PathVariable String userPassword) {
		log.info("UserController userValiadtion method start");	
		User user =null;
		try {
			user = service.validateUser(userEmail,userPassword);
			log.info("UserController userValiadtion method end");	
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/users/updatepassword")
	public ResponseEntity<User> updateUserPassword(@RequestBody PasswordUpdateRequest request){
		log.info("UserController updateUserPassword method start");	
		log.info("Update User password : "+request);
		User user =null;
		try {
			user = service.updatePassword(request.getEmail(),request.getNewPassword());
			log.info("UserController updateUserPassword method end");	
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		catch(Exception e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
