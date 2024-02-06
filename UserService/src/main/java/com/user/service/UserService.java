package com.user.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.user.bean.UserBean;
import com.user.exception.UserNotFoundByIdException;
import com.user.structure.ResponseStructure;

public interface UserService {
	
	
	public ResponseEntity<ResponseStructure<UserBean>> save(UserBean user);
	public ResponseEntity<ResponseStructure<UserBean>> update(UserBean user) throws UserNotFoundByIdException;
	public ResponseEntity<ResponseStructure<UserBean>> delete(int userId) throws UserNotFoundByIdException;
	public ResponseEntity<ResponseStructure<UserBean>> getById(int userId) throws UserNotFoundByIdException;
	public ResponseEntity<ResponseStructure<List<UserBean>>> getAll();
	

	
	

}
