package com.user.controller;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.user.bean.User;
import com.user.exception.DuplicateMobileNumberException;
import com.user.exception.DuplicateEmailIdException;
import com.user.exception.UserNotFoundByIdException;
import com.user.service.UserService;



@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	
	@Mock
	private UserService userService;
	
	
	@InjectMocks
	private UserController userController;
	
	private User userBean;

	@BeforeEach
	public void setUp() {
		userBean = new User();
		userBean.setUserGender('F');
		userBean.setUserId(1);
		userBean.setUserEmail("anushauppar1998@gmail.com");
	}	
	
	@Test
	public void testSave() throws DuplicateEmailIdException, DuplicateMobileNumberException {
		
		when(userService.saveUserDetails(any(User.class))).thenReturn(userBean);		
		ResponseEntity<User> response = userController.saveUserDetails(userBean);
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(userBean,response.getBody());		
	}
	
	@Test
	public void getById() throws UserNotFoundByIdException  {
		
		when(userService.getUserDetailsByUserId(1)).thenReturn(userBean);		
		ResponseEntity<User> response = userController.getUserDetailsByUserId(1);
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(userBean,response.getBody());		
	}
	
	
	@Test
	public void getUserDetails() throws UserNotFoundByIdException  {				
		List<User> userList = new ArrayList<>();
		userList.add(userBean);
		when(userService.getAllUserDetails()).thenReturn(userList);
		ResponseEntity<List<User>> response = userController.getAllUserDetails();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(userList, response.getBody());			
	}
		
	@Test
	public void deleteUserById() throws UserNotFoundByIdException {		
		when(userService.deleteUserDetailsByUserId(1)).thenReturn(userBean);
		ResponseEntity<User> response = userController.deleteUserDetailsByUserId(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(userBean, response.getBody());	
	}
		
	@Test
	public void validateUser() throws UserNotFoundByIdException {		
		when(userService.validateUser("anushauppar1998@gmail.com", "anuwith.com")).thenReturn(userBean);
		ResponseEntity<User> response = userController.userValiadtion("anushauppar1998@gmail.com", "anuwith.com");
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(userBean, response.getBody());
	}
	
	@Test
	public void updatePassword() throws UserNotFoundByIdException {		
		when(userService.updatePassword("anushauppar1998@gmail.com", "anuwith.com")).thenReturn(userBean);
		ResponseEntity<User> response = userController.userValiadtion("anushauppar1998@gmail.com", "anuwith.com");
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(userBean, response.getBody());
	}
		
	
}
