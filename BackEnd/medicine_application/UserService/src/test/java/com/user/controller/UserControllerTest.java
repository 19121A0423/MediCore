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

import com.user.bean.UserBean;
import com.user.exception.DuplicateMobileNumberException;
import com.user.exception.BothEmailIdAndMobileNumberIsExistException;
import com.user.exception.DuplicateEmailIdException;
import com.user.exception.UserNotFoundByIdException;
import com.user.service.UserService;



@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	
	@Mock
	private UserService userService;
	
	
	@InjectMocks
	private UserController userController;
	
	private UserBean userBean;

	@BeforeEach
	public void setUp() {
		userBean = new UserBean();
		userBean.setUserGender('F');
		userBean.setUserId(1);
		userBean.setUserEmail("anushauppar1998@gmail.com");
	}	
	
	@Test
	public void testSave() throws DuplicateEmailIdException, DuplicateMobileNumberException, BothEmailIdAndMobileNumberIsExistException {
		
		when(userService.saveUserDetails(any(UserBean.class))).thenReturn(userBean);		
		ResponseEntity<UserBean> response = userController.saveUserDetails(userBean);
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(userBean,response.getBody());		
	}
	
	@Test
	public void getById() throws UserNotFoundByIdException  {
		
		when(userService.getUserDetailsByUserId(1)).thenReturn(userBean);		
		ResponseEntity<UserBean> response = userController.getUserDetailsByUserId(1);
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(userBean,response.getBody());		
	}
	
	
	@Test
	public void getUserDetails() throws UserNotFoundByIdException  {				
		List<UserBean> userList = new ArrayList<>();
		userList.add(userBean);
		when(userService.getAllUserDetails()).thenReturn(userList);
		ResponseEntity<List<UserBean>> response = userController.getAllUserDetails();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(userList, response.getBody());			
	}
		
	@Test
	public void deleteUserById() throws UserNotFoundByIdException {		
		when(userService.deleteUserDetailsByUserId(1)).thenReturn(userBean);
		ResponseEntity<UserBean> response = userController.deleteUserDetailsByUserId(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(userBean, response.getBody());	
	}
		
//	@Test
//	public void validateUser() throws UserNotFoundByIdException {		
//		when(userService.validateUser("anushauppar1998@gmail.com", "anuwith.com")).thenReturn(userBean);
//		ResponseEntity<UserBean> response = userController.user("anushauppar1998@gmail.com", "anuwith.com");
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals(userBean, response.getBody());
//	}
	

	
}
