package com.user.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.user.bean.UserBean;
import com.user.entity.User;
import com.user.exception.DublicateEmailIdException;
import com.user.exception.DublicateMobileNumberException;
import com.user.exception.UserNotFoundByIdException;
import com.user.repository.UserRepo;
import com.user.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class UserServiceImpl implements UserService {
	
	private static Logger log = LoggerFactory
			.getLogger(UserServiceImpl.class.getSimpleName());
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public UserBean save(UserBean user) throws DublicateEmailIdException, DublicateMobileNumberException {
		
		log.info("UserServiceImpl save method start {} "+user);
		
		if(user.getUserEmail()==null || user.getUserPassword()==null) {
			throw new IllegalArgumentException("User Values cannot be null");
		}
		
		String email=userRepo.getEmail(user.getUserEmail());
		if(email!=null) {
			throw new DublicateEmailIdException("User Details Are Not Saved");
		}
		Long mobileNumber=userRepo.getMobileNumber(user.getUserMobileNumber());
		if(mobileNumber!=null) {
			throw new DublicateMobileNumberException("User Details Are Not Saved");
		}
		
		
		User userEntity = new User();
		userEntity=	beanToEntity(userEntity, user);	
		userEntity = userRepo.save(userEntity);	
		user=entityToBean(userEntity, user);
		sendMail(user);
				
		
		
		log.info("UserServiceImpl save method end {} "+user);	
		
		return user;
	}
	
	
	public void sendMail(UserBean user) {
		
		log.info("User  service implementation send mail method start {} "+user);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getUserEmail());
		mail.setSubject(" Registration ");
		mail.setText("Hello " + user.getUserName() + " your account created succesfully as a "
				+ user.getUserRole());
		mail.setSentDate(new Date());
		javaMailSender.send(mail);
		log.info("User  service implementation send mail method end {} "+user);

	}

	@Override
	public UserBean update(UserBean user) throws UserNotFoundByIdException {
		
		log.info("User  service implementation update method start {} "+user);
		
		if(user.getUserId()==0) {
			throw new IllegalArgumentException("User Id cannot be null");
		}
		
		Optional<User> optional = userRepo.findById(user.getUserId());
		
		if(optional.isPresent()) {	
			User userEntity = optional.get();
			userEntity=beanToEntity(userEntity, user);
			userEntity= userRepo.save(userEntity);
			user=entityToBean(userEntity, user);
					
			return user;
			
		}else {
			throw new UserNotFoundByIdException("User does not exist by this "+user.getUserId());
		}
	}

	@Override
	public UserBean getById(int userId) throws UserNotFoundByIdException {
		log.info("User  service implementation getById method start {} "+userId);
		if(userId==0) {
			throw new IllegalArgumentException("User Id cannot be null");
		}
		Optional<User> optional = userRepo.findById(userId);
		if(optional.isPresent()){
			
			User userEntity = optional.get();
			UserBean bean = new UserBean();
			bean=entityToBean(userEntity, bean);
		
			
			log.info("User  service implementation getById method end {} "+userId);
			return bean;
			
		}else {
			 throw new UserNotFoundByIdException("User Not Found By This Id "+userId) ;
		}		
	}
	
	@Override
	public UserBean delete(int userId) throws UserNotFoundByIdException {
		log.info("User  service implementation delete method start {} "+userId);
		if(userId==0) {
			throw new IllegalArgumentException("User Id cannot be null");
		}
		Optional<User> optional = userRepo.findById(userId);
		
		if(optional.isPresent()){	
			User userEntity=optional.get();
			userRepo.deleteById(userId);			
			UserBean bean = new UserBean();		
			bean =  entityToBean(userEntity, bean);
		    
			
			return bean;
		}else {
			throw new UserNotFoundByIdException("User Not Present By This Id "+userId);
		}
	}
	@Override
	public List<UserBean> getAll() {
		
		log.info("User  service implementation get all method start ");

		List<User> users = userRepo.findAll(); 
		List<UserBean> usersList = new ArrayList<>();
		for(User user :users) {
			
			UserBean bean = new UserBean();			
			bean=entityToBean(user, bean);
		    usersList.add(bean);	    
		}
		log.info("User  service implementation get all method end {} "+usersList);	
		return usersList;
	}

		public User beanToEntity(User userEntity, UserBean bean) {
			log.info("User  service implementation beanToEntity method start {}"+userEntity);

			userEntity.setName(bean.getUserName());
			userEntity.setEmail(bean.getUserEmail());
			userEntity.setGender(bean.getUserGender());
			userEntity.setMobile_number(bean.getUserMobileNumber());
			userEntity.setPassword(bean.getUserPassword());
			userEntity.setUser_role(bean.getUserRole());
			userEntity.setStatus(bean.getUserStatus());
			
			log.info("User  service implementation beanToEntity method end "+bean);
			return userEntity;
			
		}
		
		public UserBean entityToBean(User userEntity, UserBean bean) {
			
			
			log.info("User  service implementation beanToEntity method start {}"+bean);

			bean.setUserId(userEntity.getUser_id());
			bean.setUserEmail(userEntity.getEmail());
			bean.setUserPassword(userEntity.getPassword());
			bean.setUserGender(userEntity.getGender());
			bean.setUserMobileNumber(userEntity.getMobile_number());
			bean.setUserName(userEntity.getName());
			bean.setUserRole(userEntity.getUser_role());
			bean.setUserStatus(userEntity.getStatus());
			
			log.info("User  service implementation beanToEntity method start {}"+userEntity);

			return bean;
		}


	@Override
	public UserBean validateUser(String email, String password) throws UserNotFoundByIdException {
		User user = null;
		if (email != null && password != null) {
			user = userRepo.findByEmailAndPassword(email, password);
		}

		if (user != null) {
			UserBean userBean = new UserBean();
			userBean = entityToBean(user, userBean);
			return userBean;
		} else {
			throw new UserNotFoundByIdException("User not found ");
		}
	}

	@Override
	public UserBean updatePassword(String email, String password) throws UserNotFoundByIdException {
		User user = null;
		if (email != null) {
			user = userRepo.findByEmail(email);
		}
		

		if (user != null && password!=null) {
			user.setPassword(password);
			userRepo.save(user);
			UserBean userBean = new UserBean();
			userBean = entityToBean(user, userBean);
			return userBean;
		} else {
			throw new UserNotFoundByIdException("User not found ");
		}
	}

}
