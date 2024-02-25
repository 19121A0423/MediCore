package com.user.service;
import java.util.List;


import com.user.bean.UserBean;
import com.user.exception.DublicateEmailIdException;
import com.user.exception.DublicateMobileNumberException;
import com.user.exception.UserNotFoundByIdException;

public interface UserService {
	
	
	public UserBean save(UserBean user) throws DublicateEmailIdException, DublicateMobileNumberException;
	public UserBean update(UserBean user) throws UserNotFoundByIdException;
	public UserBean delete(int userId) throws UserNotFoundByIdException;
	public UserBean getById(int userId) throws UserNotFoundByIdException;
	public List<UserBean> getAll();
	
	
	public UserBean validateUser(String email, String password) throws UserNotFoundByIdException;
	public UserBean updatePassword(String email,String password) throws UserNotFoundByIdException;
	

}
