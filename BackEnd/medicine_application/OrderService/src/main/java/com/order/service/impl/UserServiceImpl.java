package com.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.order.bean.UserBean;
import com.order.exceptions.UserNotFoundException;
import com.order.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public UserBean getUserBean(int id) throws UserNotFoundException {

		String url = "http://localhost:8084/userservice/users/"+id ;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<UserBean> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity , UserBean.class);
		UserBean user = response.getBody();
		
		if(user == null) {
			throw new UserNotFoundException("User not found with id : "+id );
		}
		else {
			return user;
		}
	}

}
