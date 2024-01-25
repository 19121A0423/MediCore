package com.order.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity implements Serializable{

	private static final long serialVersionUID = -4194911857077683391L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer orderId;

	@Column(name = "ordered_date")
	private LocalDate orderedDate;

	@Column(name = "status")
	private String status;

	@Column(name = "address_id")
	private Integer addressId;

	@Column(name = "cart_id")
	private Integer cartId;

	@OneToOne(mappedBy = "order")
	@JsonIgnore
	private PaymentEntity payment;

	public OrderEntity() {
		super();
	}

	public OrderEntity(Integer orderId, LocalDate orderedDate, String status, Integer addressId, Integer cartId, PaymentEntity payment) {
		super();
		this.orderId = orderId;
		this.orderedDate = orderedDate;
		this.status = status;
		this.addressId = addressId;
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

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public PaymentEntity getPayment() {
		return payment;
	}

	public void setPayment(PaymentEntity payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "OrderEntity [orderId=" + orderId + ", orderedDate=" + orderedDate + ", status=" + status
				+ ", addressId=" + addressId + ", cartId=" + cartId + ", payment=" + payment + "]";
	}

}
