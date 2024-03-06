package com.user.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.bean.User;
import com.user.entity.UserEntity;
import com.user.exception.DuplicateMobileNumberException;
import com.user.exception.DuplicateEmailIdException;
import com.user.exception.UserNotFoundByIdException;
import com.user.repository.UserRepository;
import com.user.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class UserServiceImpl implements UserService {

	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class.getSimpleName());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@Autowired
	private ObjectMapper mapper; 

	@Override
	public User saveUserDetails(User userBean) throws DuplicateEmailIdException, DuplicateMobileNumberException {
		log.info("UserServiceImpl save method start {} " + userBean);

		if (userBean.getUserEmail() == null || userBean.getUserPassword() == null) {
			throw new IllegalArgumentException("User Values cannot be null");
		}
		UserEntity existingUser = userRepository.findByUserEmailOrUserMobileNumber(userBean.getUserEmail(), userBean.getUserMobileNumber());
		if (existingUser != null) {
			if (existingUser.getUserEmail() != null) {
				throw new DuplicateEmailIdException("Duplicate Emaild");
			}
			if (existingUser.getUserMobileNumber() != null) {
				throw new DuplicateMobileNumberException("Duplicate Mobile Number");
			}
		}
		UserEntity userEntity = new UserEntity();
		userEntity = mapper.convertValue(userBean,UserEntity.class);
		userEntity = userRepository.save(userEntity);
		userBean = mapper.convertValue(userEntity, User.class);
		sendMail(userBean);

		log.info("UserServiceImpl save method end {} " + userBean);

		return userBean;
	}

	@Override
	public User updateUserDetails(User userBean) throws UserNotFoundByIdException {

		log.info("User  service implementation update method start {} " + userBean);
		
		if (userBean.getUserId() == null) {
			throw new IllegalArgumentException("User Id cannot be Empty");
		}
		Optional<UserEntity> optional = userRepository.findById(userBean.getUserId());
		if (optional.isPresent()) {
			UserEntity userEntity = optional.get();
			
			userEntity = mapper.convertValue(userBean, UserEntity.class);
			userEntity = userRepository.save(userEntity);			
			userBean = mapper.convertValue(userEntity, User.class);
			return userBean;
		} else {
			throw new UserNotFoundByIdException("User does not exist by this " + userBean.getUserId());
		}
	}

	@Override
	public User getUserDetailsByUserId(Integer userId) throws UserNotFoundByIdException {

		log.info("User service implementation getById method start {} " + userId);
		if (userId == null) {
			throw new IllegalArgumentException("User Id cannot be null");
		}
		Optional<UserEntity> optional = userRepository.findById(userId);
		if (optional.isPresent()) {

			UserEntity userEntity = optional.get();
			User bean = new User();
			bean = mapper.convertValue(userEntity, User.class);

		log.info("User  service implementation getById method end {} " + userId);
			return bean;

		} else {
			
			log.error(" User Not Found By This Id {} "+userId);		
			throw new UserNotFoundByIdException("User Not Found By This Id " + userId);
		}
	}

	@Override
	public User deleteUserDetailsByUserId(Integer userId) throws UserNotFoundByIdException {
		log.info("User  service implementation delete method start {} " + userId);
		if (userId == null) {
			throw new IllegalArgumentException("User Id cannot be null");
		}
		Optional<UserEntity> optional = userRepository.findById(userId);

		if (optional.isPresent()) {
			UserEntity userEntity = optional.get();
			userRepository.deleteById(userId);
			User bean = new User();
			bean =mapper.convertValue(userEntity, User.class);

			return bean;
		} else {
			throw new UserNotFoundByIdException("User Not Exist By This Id " + userId);
		}
	}

	@Override
	public List<User> getAllUserDetails() {

		log.info("User  service implementation get all method start ");

		List<UserEntity> users = userRepository.findAll();
		List<User> usersList = new ArrayList<>();
		for (UserEntity user : users) {
			User bean = new User();
			bean = mapper.convertValue(user, User.class);
			usersList.add(bean);
		}
		log.info("User  service implementation get all method end {} " + usersList);
		return usersList;
	}

	@Override
	public User validateUser(String userEmail, String  userPassword) throws UserNotFoundByIdException {
		log.info("User Service implementation validateUser method start");
		UserEntity user = null;
		if (userEmail != null && userPassword != null) {
			user = userRepository.findByUserEmailAndUserPassword(userEmail, userPassword);
		}

		if (user != null) {
			User userBean = new User();
			userBean = mapper.convertValue(user, User.class);
			log.info("User Service implementation validateUser method end");
			return userBean;
		} else {
			throw new UserNotFoundByIdException("User not found ");
		}
	}

	@Override
	public User updatePassword(String userEmail, String userPassword) throws UserNotFoundByIdException {
		log.info("User Service implementation updatePassword method start ");
		UserEntity user = null;
		if (userEmail != null) {
			user = userRepository.findByUserEmail(userEmail);
		}

		if (user != null && userPassword != null) {
			user.setUserPassword(userPassword);
			userRepository.save(user);
			User userBean = new User();
			userBean =mapper.convertValue(user, User.class);
			log.info("User Service implementation updatePassword method end");
			return userBean;
		} else {
			throw new UserNotFoundByIdException("User not found ");
		}
	}

//	public UserBean entityToBean(User userEntity, UserBean bean) {
//
//		log.info("User  service implementation beanToEntity method start {}" + bean);
//
//		bean.setUserId(userEntity.getUser_id());
//		bean.setUserEmail(userEntity.getEmail());
//		bean.setUserPassword(userEntity.getPassword());
//		bean.setUserGender(userEntity.getGender());
//		bean.setUserMobileNumber(userEntity.getMobileNumber());
//		bean.setUserName(userEntity.getName());
//		bean.setUserRole(userEntity.getUserRole());
//		bean.setUserStatus(userEntity.getStatus());
//
//		log.info("User service implementation beanToEntity method start {}" + userEntity);
//
//		return bean;
//	}

//	public User beanToEntity(User userEntity, UserBean bean) {
//		log.info("User  service implementation beanToEntity method start {}" + userEntity);
//
//		userEntity.setName(bean.getUserName());
//		userEntity.setEmail(bean.getUserEmail());
//		userEntity.setGender(bean.getUserGender());
//		userEntity.setMobileNumber(bean.getUserMobileNumber());
//		userEntity.setPassword(bean.getUserPassword());
//		userEntity.setUserRole(bean.getUserRole());
//		userEntity.setStatus(bean.getUserStatus());
//
//		log.info("User  service implementation beanToEntity method end " + bean);
//		return userEntity;
//
//	}

	public void sendMail(User user) {

		log.info("User  service implementation send mail method start {} " + user);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getUserEmail());
		mail.setSubject(" Registration ");
		mail.setText("Hello " + user.getUserName() + " your account created succesfully as a " + user.getUserRole());
		mail.setSentDate(new Date());
		javaMailSender.send(mail);
		log.info("User  service implementation send mail method end {} " + user);

	}

}
