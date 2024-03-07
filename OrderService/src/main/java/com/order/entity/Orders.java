package com.order.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders implements Serializable{

	private static final long serialVersionUID = -4194911857077683391L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer orderId;

	@Column(name = "ordered_date")
	private LocalDateTime orderedDate;

	@Column(name = "status")
	private String status;
	
	@ManyToOne
    @JoinColumn(name = "address_id")
	private Address address;

	@Column(name = "cart_id")
	private Integer cartId;

	@OneToOne(mappedBy = "order")
	@JsonIgnore
	private Payment payment;
	
	@OneToOne(mappedBy = "order")
	@JsonIgnore
	private Feedback feedback;

	public Orders() {
		super();
	}

	public Orders(Integer orderId, LocalDateTime orderedDate, String status, Address address, Integer cartId,
			Payment payment, Feedback feedback) {
		super();
		this.orderId = orderId;
		this.orderedDate = orderedDate;
		this.status = status;
		this.address = address;
		this.cartId = cartId;
		this.payment = payment;
		this.feedback = feedback;
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

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "OrderEntity [orderId=" + orderId + ", orderedDate=" + orderedDate + ", status=" + status + ", address="
				+ address + ", cartId=" + cartId + ", payment=" + payment + ", feedback=" + feedback + "]";
	}

	

}
