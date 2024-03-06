package com.user.repo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.user.bean.UserBean;
import com.user.exception.DuplicateMobileNumberException;
import com.user.exception.DuplicateEmailIdException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	
	@Test
	public void testSave() throws DuplicateEmailIdException, DuplicateMobileNumberException {
//		when(userService.save(any(UserBean.class))).thenReturn(userBean);
		
//		ResponseEntity<UserBean> response = userController.save(userBean);
		
		
		
//		assertEquals(HttpStatus.OK,response.getStatusCode());
//		assertEquals(userBean,response.getBody());
		
	}
}

