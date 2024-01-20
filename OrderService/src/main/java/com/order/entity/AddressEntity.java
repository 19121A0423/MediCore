package com.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="address")
public class AddressEntity {
	
	private int addressId;
	private String streetName;
	private String city;
	private String state;
	private long pinCode;
	private int userId;
	private String status;

	public AddressEntity() {
		super();
	}

	public AddressEntity(int addressId, String streetName, String city, String state, long pinCode, int userId,
			String status) {
		super();
		this.addressId = addressId;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
		this.userId = userId;
		this.status = status;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getPinCode() {
		return pinCode;
	}

	public void setPinCode(long pinCode) {
		this.pinCode = pinCode;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", streetName=" + streetName + ", city=" + city + ", state=" + state
				+ ", pinCode=" + pinCode + ", userId=" + userId + ", status=" + status + "]";
	}

}
