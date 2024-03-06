package com.user.service;
import java.util.List;


import com.user.bean.User;
import com.user.exception.DuplicateMobileNumberException;
import com.user.exception.DuplicateEmailIdException;
import com.user.exception.UserNotFoundByIdException;

public interface UserService {
	
	
	public User saveUserDetails(User user) throws DuplicateEmailIdException, DuplicateMobileNumberException;
	public User updateUserDetails(User user) throws UserNotFoundByIdException;
	public User deleteUserDetailsByUserId(Integer userId) throws UserNotFoundByIdException;
	public User getUserDetailsByUserId(Integer userId) throws UserNotFoundByIdException;
	public List<User> getAllUserDetails();
	
	
	public User validateUser(String email, String password) throws UserNotFoundByIdException;
	public User updatePassword(String email,String password) throws UserNotFoundByIdException;
	

}
