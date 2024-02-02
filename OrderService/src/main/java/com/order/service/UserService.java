package com.order.service;

import com.order.bean.UserBean;
import com.order.exceptions.UserNotFoundException;

public interface UserService {

	UserBean getUserBean(int id) throws UserNotFoundException;
	
}
