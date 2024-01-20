package com.order.entity;

import java.util.Date;

public class PaymentEntity {

	private int paymentId;
	private String paymentMode;
	private double amount;
	private String status;
	private Date paymentDate;
	private int orderId;

	public PaymentEntity() {
		super();
	}

	public PaymentEntity(int paymentId, String paymentMode, double amount, String status, Date paymentDate, int orderId) {
		super();
		this.paymentId = paymentId;
		this.paymentMode = paymentMode;
		this.amount = amount;
		this.status = status;
		this.paymentDate = paymentDate;
		this.orderId = orderId;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", paymentMode=" + paymentMode + ", amount=" + amount + ", status="
				+ status + ", paymentDate=" + paymentDate + ", orderId=" + orderId + "]";
	}

}
