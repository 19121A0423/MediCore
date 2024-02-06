package com.order.bean;

import java.io.Serializable;
import java.time.LocalDate;

public class Orders implements Serializable{

	private static final long serialVersionUID = 4226841471917030749L;
	
	private Integer orderId;
	private LocalDate orderedDate;
	private String status;
	private Address address;
	private Integer cartId;
	private Payment payment;

	public Orders() {
		super();
	}

	public Orders(Integer orderId, LocalDate orderedDate, String status, Address address, Integer cartId, Payment payment) {
		super();
		this.orderId = orderId;
		this.orderedDate = orderedDate;
		this.status = status;
		this.address = address;
		this.cartId = cartId;
		this.payment = payment;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public LocalDate getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(LocalDate orderedDate) {
		this.orderedDate = orderedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", orderedDate=" + orderedDate + ", status=" + status + ", address="
				+ address + ", cartId=" + cartId + ", payment=" + payment + "]";
	}

	

}
