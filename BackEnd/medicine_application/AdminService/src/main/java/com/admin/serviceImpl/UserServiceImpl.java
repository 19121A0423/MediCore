package com.admin.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.admin.bean.UserBean;
import com.admin.service.UserService;
import com.admin.structure.ResponseStructure;


@Service
public class UserServiceImpl  implements UserService{

	
	@Autowired
	private RestTemplate template;
	
	@Override
	public UserBean getUserBean(int id) {
		
		String url = "http://localhost:8084/userservice/users/"+id;
		
		ParameterizedTypeReference<UserBean> responseType =
		        new ParameterizedTypeReference<UserBean>() {};
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<UserBean> responseEntity = template.exchange(url, HttpMethod.GET, httpEntity,responseType);
		
		UserBean data = responseEntity.getBody();
		
		System.out.println(data);	
		return responseEntity.getBody();
		
	}

}
