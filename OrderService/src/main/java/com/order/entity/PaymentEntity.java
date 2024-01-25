package com.order.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment")
public class PaymentEntity implements Serializable{
	
	private static final long serialVersionUID = 5167861301694073857L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Integer paymentId;

	@Column(name = "payment_mode")
	private String paymentMode;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "status")
	private String status;

	@Column(name = "payment_date")
	private LocalDate paymentDate;

	@OneToOne
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	@JsonIgnore
	private OrderEntity order;

//	@Column(name="order_id")
//	private int orderId;

	public PaymentEntity() {
		super();
	}

	public PaymentEntity(Integer paymentId, String paymentMode, Double amount, String status, LocalDate paymentDate,
			OrderEntity order) {
		super();
		this.paymentId = paymentId;
		this.paymentMode = paymentMode;
		this.amount = amount;
		this.status = status;
		this.paymentDate = paymentDate;
		this.order = order;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "PaymentEntity [paymentId=" + paymentId + ", paymentMode=" + paymentMode + ", amount=" + amount
				+ ", status=" + status + ", paymentDate=" + paymentDate + ", order=" + order + "]";
	}

}
