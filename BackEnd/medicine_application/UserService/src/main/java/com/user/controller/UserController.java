package com.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.bean.PasswordUpdateRequest;
import com.user.bean.UserBean;
import com.user.exception.DuplicateMobileNumberException;
import com.user.exception.EmailNotFoundException;
import com.user.exception.InvalidOTPException;
import com.user.exception.BothEmailIdAndMobileNumberIsExistException;
import com.user.exception.DuplicateEmailIdException;
import com.user.exception.UserNotFoundByIdException;
import com.user.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
	
	private static Logger log = LoggerFactory
			.getLogger(UserController.class.getSimpleName());

	@Autowired
	private UserService service;

	@PostMapping("/save")
	public ResponseEntity<UserBean> saveUserDetails(@RequestBody UserBean user) throws DuplicateEmailIdException, DuplicateMobileNumberException, BothEmailIdAndMobileNumberIsExistException {
		log.info("UserController save method start {}"+user);	
		UserBean userBean=null;
		try {
			 userBean = service.saveUserDetails(user);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		log.info("UserController save method end{}"+user);	
		 return ResponseEntity.status(HttpStatus.OK).body(userBean);
	}

	/**
	 * 
	 * @param user
	 * @return 
	 * @throws UserNotFoundByIdException
	 */
	@PutMapping("/update")
	public ResponseEntity<UserBean> updateUserDetails(@RequestBody UserBean user) throws UserNotFoundByIdException {
		log.info("UserController update method start {}"+user);	
		UserBean userBean=null;
		try {
			 userBean = service.updateUserDetails(user);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		log.info("UserController update method 	end {}"+user);	
		 return ResponseEntity.status(HttpStatus.OK).body(userBean);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserBean> getUserDetailsByUserId(@PathVariable int userId) throws UserNotFoundByIdException {
		
		log.info("UserController getById method start {}"+userId);	
		UserBean userBean=null;
		try {
			 userBean = service.getUserDetailsByUserId(userId);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		log.info("UserController getById method 	end {}"+userId);	
		
		return ResponseEntity.status(HttpStatus.OK).body(userBean);
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<UserBean> deleteUserDetailsByUserId(@PathVariable int userId) throws UserNotFoundByIdException {
		log.info("UserController delete method start {}"+userId);	
		UserBean userBean=null;
		try {
			  userBean = service.deleteUserDetailsByUserId(userId);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		log.info("UserController delete method 	end {}"+userId);			
		return ResponseEntity.status(HttpStatus.OK).body(userBean);
	}

	@GetMapping("/getall")
	public ResponseEntity<List<UserBean>> getAllUserDetails() {
		log.info("UserController getAll method start");	
		 List<UserBean> usersList = service.getAllUserDetails();
		log.info("UserController getAll method end");	
		return ResponseEntity.status(HttpStatus.OK).body(usersList);
	}

	
	@GetMapping("/validate/{userEmail}/{userPassword}")
	public ResponseEntity<UserBean> userValiadtion(@PathVariable String userEmail, @PathVariable String userPassword) {
		log.info("UserController userValiadtion method start");	
		UserBean user =null;
		try {
			user = service.validateUser(userEmail,userPassword);
			log.info("UserController userValiadtion method end");	
			return new ResponseEntity<UserBean>(user,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/updatepassword")
	public ResponseEntity<UserBean> updateUserPassword(@RequestBody PasswordUpdateRequest request){
		log.info("UserController updateUserPassword method start");	
		log.info("Update User password : "+request);
		UserBean user =null;
		try {
			user = service.updatePassword(request.getEmail(),request.getNewPassword());
			log.info("UserController updateUserPassword method end");	
			return new ResponseEntity<UserBean>(user,HttpStatus.OK);
		}
		catch(Exception e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/generateotp/{email}")
	public ResponseEntity<UserBean> generateOtpAndSendEmail(@PathVariable("email") String email) {
		log.info("UserController generateOtpAndSendEmail method start");
		try {
			UserBean user = service.forgetPassword(email);
			if (user != null) {
				log.info("UserController generateOtpAndSendEmail method start");
				return new ResponseEntity<UserBean>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<UserBean>(HttpStatus.UNAUTHORIZED);
			}

		} catch (EmailNotFoundException e) {
			log.info("UserController generateOtpAndSendEmail method end");
			return new ResponseEntity<UserBean>(HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping("/verify/{email}/{enteredOtp}")
	public ResponseEntity<String> verifyOtp(@PathVariable String email, @PathVariable String enteredOtp) {
		log.info("UserController verifyOtp method start");
		try {
			if (service.verifyOtp(email, enteredOtp)) {
				String jsonString = "{\"message\":\"Verified Successfully\"}";
				log.info("verify the otp by using email is done");
				log.info("UserController verifyOtp method end");
				return ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json").body(jsonString);
			} else {
				log.info("Sending  the invalid otp");
				String jsonString = "{\"message\":\"Invalid OTP\"}";
				log.info("UserController verifyOtp method end");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Content-Type", "application/json")
						.body(jsonString);
			}
		} catch (InvalidOTPException e) {

			String jsonString = "{\"message\":\"wrong otp\"}";
			log.error("error handled");
			log.info("UserController verifyOtp method end");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Content-Type", "application/json")
					.body(jsonString);

		}
	}

}
