package com.order.entity;

import java.util.Date;

public class OrderEntity {

	private int orderId;
	private Date orderedDate;
	private String status;
	private int addressId;
	private int cartId;

	public OrderEntity() {
		super();
	}

	public OrderEntity(int orderId, Date orderedDate, String status, int addressId, int cartId) {
		super();
		this.orderId = orderId;
		this.orderedDate = orderedDate;
		this.status = status;
		this.addressId = addressId;
		this.cartId = cartId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderedDate=" + orderedDate + ", status=" + status + ", addressId="
				+ addressId + ", cartId=" + cartId + "]";
	}

}
