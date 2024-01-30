package com.ooms.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.ooms.bean.UserBean;
import com.ooms.entity.User;
import com.ooms.exception.EmailIdNotFoundException;
import com.ooms.exception.InvalidPasswordException;
import com.ooms.exception.UserNotFoundByIdException;

import com.ooms.repository.UserRepo;
import com.ooms.service.UserService;
import com.ooms.structure.ResponseStructure;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
		
	

		
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public ResponseEntity<ResponseStructure<UserBean>> save(UserBean user) {
		
		User userEntity = new User();
		userEntity.setName(user.getUserName());
		userEntity.setEmail(user.getUserEmail());
		userEntity.setMobile_number(user.getUserMobileNumber());
		userEntity.setPassword(user.getUserPassword());
		userEntity.setGender(user.getUserGender());
		userEntity.setUser_role(user.getUserRole());
		userEntity.setStatus(user.getUserStatus());
	
		userRepo.save(userEntity);
	
		
		ResponseStructure<UserBean> structure = new ResponseStructure<>();
		structure.setData(user);
		structure.setMessage("Data Saved Successfully !!!");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<UserBean>>(structure,HttpStatus.OK) ;
	}
	
	
	public void sendMail(UserBean user) {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getUserEmail());
		mail.setSubject(" Registration ");
		mail.setText("Hello " + user.getUserName() + " your account created succesfully as a "
				+ user.getUserRole());
		mail.setSentDate(new Date());
		javaMailSender.send(mail);

	}

	@Override
	public ResponseEntity<ResponseStructure<UserBean>> update(UserBean user) {
		
		
		Optional<User> optional = userRepo.findById(user.getUserId());
		
		if(optional.isPresent()) {	
			
			User userEntity = optional.get();
			
			userEntity.setName(user.getUserName());
			userEntity.setEmail(user.getUserEmail());
			userEntity.setMobile_number(user.getUserMobileNumber());
			userEntity.setPassword(user.getUserPassword());
			userEntity.setGender(user.getUserGender());
			userEntity.setUser_role(user.getUserRole());
			userEntity.setStatus(user.getUserStatus());	
			
			/* User save = */ userRepo.save(userEntity);
			
			/*
			 * user.setUserName(save.getName()); user.setUserEmail(save.getEmail());
			 * user.setUserMobileNumber(save.getMobile_number());
			 * user.setUserPassword(save.getPassword());
			 * user.setUserGender(save.getGender()); user.setUserRole(save.getUser_role());
			 * user.setUserStatus(save.getStatus());
			 */
			
			
			ResponseStructure<UserBean> structure = new ResponseStructure<>();
			structure.setData(user);
			structure.setMessage("Data Saved Successfully !!!");
			structure.setStatusCode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<UserBean>>(structure,HttpStatus.OK) ;
			
		}else {
			throw new UserNotFoundByIdException("User does not exist by this "+user.getUserId());
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<UserBean>> getById(int userId) {
		 Optional<User> optional = userRepo.findById(userId);
		if(optional.isPresent()){
			
			User user = optional.get();
			UserBean bean = new UserBean();
			
		    bean.setUserName(user.getName()); 
		    bean.setUserEmail(user.getEmail());
		    bean.setUserMobileNumber(user.getMobile_number());
		    bean.setUserPassword(user.getPassword());
		    bean.setUserGender(user.getGender()); 
		    bean.setUserRole(user.getUser_role());
		    bean.setUserStatus(user.getStatus());
			
			ResponseStructure<UserBean> structure = new ResponseStructure<>();
			structure.setData(bean);
			structure.setMessage("Data fetched Successfully !!!");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<UserBean>>(structure, HttpStatus.FOUND);
			
		}else {
			 throw new UserNotFoundByIdException("User Not Found By This Id "+userId) ;
		}		
	}
	
	@Override
	public ResponseEntity<ResponseStructure<UserBean>> delete(int userId) {
		 Optional<User> optional = userRepo.findById(userId);
		 
		if(optional.isPresent()){
				
			User user = optional.get();				
			UserBean bean = new UserBean();
			userRepo.deleteById(userId);
			
		    bean.setUserName(user.getName()); 
		    bean.setUserEmail(user.getEmail());
		    bean.setUserMobileNumber(user.getMobile_number());
		    bean.setUserPassword(user.getPassword());
		    bean.setUserGender(user.getGender()); 
		    bean.setUserRole(user.getUser_role());
		    bean.setUserStatus(user.getStatus());
		    
			ResponseStructure<UserBean> structure = new ResponseStructure<>();
			structure.setData(bean);
			structure.setMessage("User data deleted successfully  !!!");
			structure.setStatusCode(HttpStatus.OK.value());	
			
			return new ResponseEntity<ResponseStructure<UserBean>>(structure, HttpStatus.OK);
		}else {
			throw new UserNotFoundByIdException("User Not Present By This Id "+userId);
		}
	}
	@Override
	public ResponseEntity<ResponseStructure<List<UserBean>>> getAll() {
		
		List<User> users = userRepo.findAll(); 
		List<UserBean> usersList = new ArrayList<>();
		for(User user :users) {
			
			UserBean bean = new UserBean();
			
		    bean.setUserName(user.getName()); 
		    bean.setUserEmail(user.getEmail());
		    bean.setUserMobileNumber(user.getMobile_number());
		    bean.setUserPassword(user.getPassword());
		    bean.setUserGender(user.getGender()); 
		    bean.setUserRole(user.getUser_role());
		    bean.setUserStatus(user.getStatus());
		    usersList.add(bean);	    
		}
		
		ResponseStructure<List<UserBean>> structure = new ResponseStructure<>();
		structure.setData(usersList);
		structure.setMessage("User List Found  successfully  !!!");
		structure.setStatusCode(HttpStatus.FOUND.value());	
		
		return new ResponseEntity<ResponseStructure<List<UserBean>>>(structure, HttpStatus.FOUND);
	}


	@Override
	public ResponseEntity<ResponseStructure<String>> userLogin(UserBean user) {
				
		String email = user.getUserEmail();
		
		User user2 = userRepo.getUserByEmail(email);
		ResponseStructure<String> structure;
		if(user2!=null) {
			System.out.println("User email exist");
			if(user2.getPassword().equals(user.getUserPassword())) {
				System.out.println("Login successfull");
				user.setUserName(user2.getName());
				structure = new ResponseStructure<>();
				structure.setData(user.getUserName());
				structure.setMessage("Hello "+user.getUserName()+" login successfully !!!");
				structure.setStatusCode(HttpStatus.ACCEPTED.value());
				
				}else {
					throw new InvalidPasswordException("Invalid Password");
				}
		}else{ 
			throw new EmailIdNotFoundException("This "+email+" Email Doesnot Exist ");
		}		
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.ACCEPTED);
	}


	
}
