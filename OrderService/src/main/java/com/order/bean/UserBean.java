package com.order.bean;

public class UserBean {
	
	private Integer userId;
	private String userName;
	private Character userGender;
	private Long userMobileNumber;
	private String userEmail;
	private String userPassword;
	private String userRole;
	private String userStatus;

	public UserBean() {
		super();
	}

	public UserBean(Integer userId, String userName, Character userGender, Long userMobileNumber, String userEmail,
			String userPassword, String userRole, String userStatus) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userGender = userGender;
		this.userMobileNumber = userMobileNumber;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userRole = userRole;
		this.userStatus = userStatus;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Character getUserGender() {
		return userGender;
	}

	public void setUserGender(Character userGender) {
		this.userGender = userGender;
	}

	public Long getUserMobileNumber() {
		return userMobileNumber;
	}

	public void setUserMobileNumber(Long userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public String toString() {
		return "UserBean [userId=" + userId + ", userName=" + userName + ", userGender=" + userGender
				+ ", userMobileNumber=" + userMobileNumber + ", userEmail=" + userEmail + ", userPassword="
				+ userPassword + ", userRole=" + userRole + ", userStatus=" + userStatus + "]";
	}
	
	
	

}
