package com.order.bean;

import java.time.LocalDate;

public class Feedback {

	private Integer feedbackId;
	private Integer userId;
	private Orders order;
	private String feedback;
	private Double ratings;
	private LocalDate feedbackDate;

	public Feedback() {
		super();
	}

	public Feedback(Integer feedbackId, Integer userId, Orders order, String feedback, Double ratings,
			LocalDate feedbackDate) {
		super();
		this.feedbackId = feedbackId;
		this.userId = userId;
		this.order = order;
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

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
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

	

}
