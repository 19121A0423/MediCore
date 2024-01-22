package com.order.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private int orderId;
	
	@Column(name="ordered_date")
	private Date orderedDate;
	
	@Column(name="status")
	private String status;
	
	@Column(name="address_id")
	private int addressId;
	
	@Column(name="cart_id")
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
