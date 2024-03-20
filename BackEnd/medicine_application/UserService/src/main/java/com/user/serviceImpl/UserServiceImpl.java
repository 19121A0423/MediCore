package com.user.serviceImpl;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.user.bean.UserBean;
import com.user.constants.CommonConstants;
import com.user.entity.OTPEntity;
import com.user.entity.User;
import com.user.exception.BothEmailIdAndMobileNumberIsExistException;
import com.user.exception.DuplicateEmailIdException;
import com.user.exception.DuplicateMobileNumberException;
import com.user.exception.EmailNotFoundException;
import com.user.exception.InvalidOTPException;
import com.user.exception.UserNotFoundByIdException;
import com.user.repository.OTPRepository;
import com.user.repository.UserRepository;
import com.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class.getSimpleName());

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OTPRepository otpRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private ObjectMapper mapper;

	@Override
	public UserBean saveUserDetails(UserBean userBean)
			throws DuplicateEmailIdException, DuplicateMobileNumberException, BothEmailIdAndMobileNumberIsExistException {
		log.info("UserServiceImpl save method start {} " + userBean);

		if (userBean.getUserEmail() == null || userBean.getUserPassword() == null) {
			throw new IllegalArgumentException("User Values cannot be null");
		}
		List<User> existingUser = userRepository.findByUserEmailOrUserMobileNumber(userBean.getUserEmail(),
				userBean.getUserMobileNumber());
		Optional<User> user = existingUser.stream().findFirst();
		if(existingUser.size()>1) {
			throw new BothEmailIdAndMobileNumberIsExistException("Both Email Id And Mobile Number is Exist Excepton");
		}
		else if (user.isPresent()) {
			if (user.get().getUserEmail().equalsIgnoreCase(userBean.getUserEmail())) {
				throw new DuplicateEmailIdException("Duplicate Emaild");
			}
			if (user.get().getUserMobileNumber().equals(userBean.getUserMobileNumber())) {
				throw new DuplicateMobileNumberException("Duplicate Mobile Number");
			}
		}
		User userEntity = new User();
		userEntity = mapper.convertValue(userBean, User.class);
		userEntity = userRepository.save(userEntity);
		userBean = mapper.convertValue(userEntity, UserBean.class);
		sendMail(userBean);

		log.info("UserServiceImpl save method end {} " + userBean);

		return userBean;
	}

	@Override
	public UserBean updateUserDetails(UserBean userBean) throws UserNotFoundByIdException {

		log.info("User  service implementation update method start {} " + userBean);

		if (userBean.getUserId() == null) {
			throw new IllegalArgumentException("User Id cannot be Empty");
		}
		Optional<User> optional = userRepository.findById(userBean.getUserId());
		if (optional.isPresent()) {
			User userEntity = optional.get();

			userEntity = mapper.convertValue(userBean, User.class);
			userEntity = userRepository.save(userEntity);
			userBean = mapper.convertValue(userEntity, UserBean.class);
			return userBean;
		} else {
			throw new UserNotFoundByIdException("User does not exist by this " + userBean.getUserId());
		}
	}

	@Override
	public UserBean getUserDetailsByUserId(Integer userId) throws UserNotFoundByIdException {

		log.info("User service implementation getById method start {} " + userId);
		if (userId == null) {
			throw new IllegalArgumentException("User Id cannot be null");
		}
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {

			User userEntity = optional.get();
			UserBean bean = new UserBean();
			bean = mapper.convertValue(userEntity, UserBean.class);

			log.info("User  service implementation getById method end {} " + userId);
			return bean;

		} else {

			log.error(" User Not Found By This Id {} " + userId);
			throw new UserNotFoundByIdException("User Not Found By This Id " + userId);
		}
	}

	@Override
	public UserBean deleteUserDetailsByUserId(Integer userId) throws UserNotFoundByIdException {
		log.info("User  service implementation delete method start {} " + userId);
		if (userId == null) {
			throw new IllegalArgumentException("User Id cannot be null");
		}
		Optional<User> optional = userRepository.findById(userId);

		if (optional.isPresent()) {
			User userEntity = optional.get();
			userRepository.deleteById(userId);
			UserBean bean = new UserBean();
			bean = mapper.convertValue(userEntity, UserBean.class);

			return bean;
		} else {
			throw new UserNotFoundByIdException("User Not Exist By This Id " + userId);
		}
	}

	@Override
	public List<UserBean> getAllUserDetails() {

		log.info("User  service implementation get all method start ");

		List<User> users = userRepository.findAll();
		List<UserBean> usersList = new ArrayList<>();
		for (User user : users) {
			UserBean bean = new UserBean();
			bean = mapper.convertValue(user, UserBean.class);
			usersList.add(bean);
		}
		log.info("User  service implementation get all method end {} " + usersList);
		return usersList;
	}

	@Override
	public UserBean validateUser(String userEmail, String userPassword) throws UserNotFoundByIdException {
		log.info("User Service implementation validateUser method start");
		User user = null;
		if (userEmail != null && userPassword != null) {
			user = userRepository.findByUserEmailAndUserPassword(userEmail, userPassword);
		}

		if (user != null) {
			UserBean userBean = new UserBean();
			userBean = mapper.convertValue(user, UserBean.class);
			log.info("User Service implementation validateUser method end");
			return userBean;
		} else {
			throw new UserNotFoundByIdException("User not found ");
		}
	}

	@Override
	public UserBean updatePassword(String userEmail, String userPassword) throws UserNotFoundByIdException {
		log.info("User Service implementation updatePassword method start ");
		User user = null;
		if (userEmail != null) {
			user = userRepository.findByUserEmail(userEmail);
		}

		if (user != null && userPassword != null) {
			user.setUserPassword(userPassword);
			userRepository.save(user);
			UserBean userBean = new UserBean();
			userBean = mapper.convertValue(user, UserBean.class);
			log.info("User Service implementation updatePassword method end");
			return userBean;
		} else {
			throw new UserNotFoundByIdException("User not found ");
		}
	}

	public void sendMail(UserBean user) {

		log.info("User  service implementation send mail method start {} " + user);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getUserEmail());
		mail.setSubject(CommonConstants.SUBJECT);
		mail.setText("Hello " + user.getUserName() + " your account created succesfully as a " + user.getUserRole());
		mail.setSentDate(new Date());
		javaMailSender.send(mail);
		log.info("User  service implementation send mail method end {} " + user);

	}

	@Override
	public String generateOtp() {
		log.info("User service implementation generateOtp method start {} ");
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		log.info("User service implementation generateOtp method end {} ");
		return String.valueOf(otp);
	}

	@Override
	public void sendOtpEmail(String email, String otp) {
		log.info("User service implementation sendOtpEmail method start {} ");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject(CommonConstants.OTPSUBJECT);
		message.setText(CommonConstants.OTPTEXT + otp);

		javaMailSender.send(message);
		log.info("User service implementation sendOtpEmail method end {} ");
	}

	@Override
	public UserBean forgetPassword(String email) throws EmailNotFoundException {
		log.info("User service implementation forgetPassword method end {} ");
		try {
			log.info("Checking if email is present or not");
			User user = userRepository.findByUserEmail(email);

			if (user != null) {
				log.info("Email is valid");
				String otp = generateOtp();
				Timestamp expirationTime = Timestamp.from(Instant.now().plus(Duration.ofMinutes(5)));
				sendOtpEmail(email, otp);
				saveOtp(email, otp, expirationTime);
				UserBean userBean = mapper.convertValue(user, UserBean.class);
				log.info("User service implementation forgetPassword method end {} ");
				return userBean;
			} else {
				log.info("Email is not valid");
				throw new EmailNotFoundException("Email not found");
			}
		} catch (EmailNotFoundException exception) {
			log.error("Email not found: ", email, exception);
			throw exception;
		}
	}

	@Override
	public void saveOtp(String email, String otp, Timestamp expirationTime) {
		log.info("User service implementation saveOtp method start {} ");
		Optional<OTPEntity> existingOtp = otpRepository.findByEmail(email);

		if (existingOtp.isPresent()) {
			existingOtp.get().setOtp(otp);
			existingOtp.get().setExpirationTime(expirationTime);
			otpRepository.save(existingOtp.get());
		} else {
			OTPEntity newOtp = new OTPEntity();
			newOtp.setEmail(email);
			newOtp.setOtp(otp);
			newOtp.setExpirationTime(expirationTime);
			otpRepository.save(newOtp);
		}
		log.info("User service implementation saveOtp method end {} ");
	}

	@Override
	public boolean verifyOtp(String email, String enteredOtp) throws InvalidOTPException {
		log.info("User service implementation verifyOtp method start {} ");
		OTPEntity otpEntity = otpRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("OTP not found"));

		Timestamp expirationTime = otpEntity.getExpirationTime();

		LocalDateTime expirationLocalDateTime = expirationTime.toInstant().atZone(ZoneId.systemDefault())
				.toLocalDateTime();

		if (expirationLocalDateTime.isBefore(LocalDateTime.now())) {
			log.info("User service implementation verifyOtp method end {} ");
			return false;
		}

		else if (!enteredOtp.equals(otpEntity.getOtp())) {
			throw new InvalidOTPException("Invalid otp");
		} else {
			log.info("User service implementation verifyOtp method end {} ");
			return true;
		}
	}
	
	@Scheduled(fixedRate = 300000) 
	public void cleanupExpiredOtps() {
		log.info("User service implementation cleanupExpiredOtps method start {} ");
		try {
			otpRepository.deleteExpiredOtps();
			log.info("User service implementation cleanupExpiredOtps method end {} ");
		} catch (Exception e) {
			log.error("User service implementation cleanupExpiredOtps method end {} " , e.getMessage());
		}
	}

}
