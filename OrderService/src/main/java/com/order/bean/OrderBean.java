package com.order.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OrderBean implements Serializable{

	private static final long serialVersionUID = 4226841471917030749L;
	
	private Integer orderId;
	private LocalDateTime orderedDate;
	private String status;
	private AddressBean address;
	private Integer cartId;
	private PaymentBean payment;

	public OrderBean() {
		super();
	}

	public OrderBean(Integer orderId, LocalDateTime orderedDate, String status, AddressBean address, Integer cartId, PaymentBean payment) {
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

	public LocalDateTime getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(LocalDateTime orderedDate) {
		this.orderedDate = orderedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AddressBean getAddress() {
		return address;
	}

	public void setAddress(AddressBean address) {
		this.address = address;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public PaymentBean getPayment() {
		return payment;
	}

	public void setPayment(PaymentBean payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", orderedDate=" + orderedDate + ", status=" + status + ", address="
				+ address + ", cartId=" + cartId + ", payment=" + payment + "]";
	}

	

}
