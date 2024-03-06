package com.user.service;
import java.util.List;


import com.user.bean.UserBean;
import com.user.exception.DuplicateMobileNumberException;
import com.user.exception.DuplicateEmailIdException;
import com.user.exception.UserNotFoundByIdException;

public interface UserService {
	
	
	public UserBean save(UserBean user) throws DuplicateEmailIdException, DuplicateMobileNumberException;
	public UserBean update(UserBean user) throws UserNotFoundByIdException;
	public UserBean delete(Integer userId) throws UserNotFoundByIdException;
	public UserBean getById(Integer userId) throws UserNotFoundByIdException;
	public List<UserBean> getAll();
	
	
	public UserBean validateUser(String email, String password) throws UserNotFoundByIdException;
	public UserBean updatePassword(String email,String password) throws UserNotFoundByIdException;
	

}
