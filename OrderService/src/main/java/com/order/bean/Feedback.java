package com.order.bean;

import java.time.LocalDate;

public class Feedback {

	private Integer feedbackId;
	private Integer userId;
	private Integer productId;
	private String feedback;
	private Double ratings;
	private LocalDate feedbackDate;

	public Feedback() {
		super();
	}

	public Feedback(Integer feedbackId, Integer userId, Integer productId, String feedback, Double ratings,
			LocalDate feedbackDate) {
		super();
		this.feedbackId = feedbackId;
		this.userId = userId;
		this.productId = productId;
		this.feedback = feedback;
		this.ratings = ratings;
		this.feedbackDate = feedbackDate;
	}

	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Double getRatings() {
		return ratings;
	}

	public void setRatings(Double ratings) {
		this.ratings = ratings;
	}

	public LocalDate getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(LocalDate feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", userId=" + userId + ", productId=" + productId + ", feedback="
				+ feedback + ", ratings=" + ratings + ", feedbackDate=" + feedbackDate + "]";
	}

}
