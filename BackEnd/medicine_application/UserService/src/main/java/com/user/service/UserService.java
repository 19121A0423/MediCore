package com.user.service;

import java.sql.Timestamp;
import java.util.List;

import com.user.bean.UserBean;
import com.user.exception.DuplicateMobileNumberException;
import com.user.exception.EmailNotFoundException;
import com.user.exception.InvalidOTPException;
import com.user.exception.BothEmailIdAndMobileNumberIsExistException;
import com.user.exception.DuplicateEmailIdException;
import com.user.exception.UserNotFoundByIdException;

public interface UserService {

	public UserBean saveUserDetails(UserBean user) throws DuplicateEmailIdException, DuplicateMobileNumberException, BothEmailIdAndMobileNumberIsExistException;

	public UserBean updateUserDetails(UserBean user) throws UserNotFoundByIdException;

	public UserBean deleteUserDetailsByUserId(Integer userId) throws UserNotFoundByIdException;

	public UserBean getUserDetailsByUserId(Integer userId) throws UserNotFoundByIdException;

	public List<UserBean> getAllUserDetails();

	public UserBean validateUser(String email, String password) throws UserNotFoundByIdException;

	public UserBean updatePassword(String email, String password) throws UserNotFoundByIdException;

	public String generateOtp();

	public void sendOtpEmail(String email, String otp);

	public void saveOtp(String email, String otp, Timestamp expirationTime);

	public UserBean forgetPassword(String email) throws EmailNotFoundException;

	public boolean verifyOtp(String email, String enteredOtp) throws InvalidOTPException;

}
