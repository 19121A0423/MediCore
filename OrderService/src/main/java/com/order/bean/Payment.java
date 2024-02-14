package com.order.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Payment implements Serializable {

	private static final long serialVersionUID = -1318822591980769570L;
	
	private Integer paymentId;
	private String paymentMode;
	private Double amount;
	private String status;
	private LocalDateTime paymentDate;
//	private int orderId;
	private Orders order;

	public Payment() {
		super();
	}
	
	public Payment(Integer paymentId, String paymentMode, Double amount, String status, LocalDateTime paymentDate, Orders order) {
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

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", paymentMode=" + paymentMode + ", amount=" + amount + ", status="
				+ status + ", paymentDate=" + paymentDate + ", order=" + order + "]";
	}
	
	

}
