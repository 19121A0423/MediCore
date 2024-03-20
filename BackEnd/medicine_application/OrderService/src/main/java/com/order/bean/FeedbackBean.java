package com.order.bean;

import java.time.LocalDateTime;

public class FeedbackBean {

	private Integer feedbackId;
	private Integer userId;
	private OrderBean order;
	private String feedback;
	private Double ratings;
	private LocalDateTime feedbackDate;

	public FeedbackBean() {
		super();
	}

	public FeedbackBean(Integer feedbackId, Integer userId, OrderBean order, String feedback, Double ratings,
			LocalDateTime feedbackDate) {
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

	public OrderBean getOrder() {
		return order;
	}

	public void setOrder(OrderBean order) {
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

	public LocalDateTime getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(LocalDateTime feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", userId=" + userId + ", order=" + order + ", feedback="
				+ feedback + ", ratings=" + ratings + ", feedbackDate=" + feedbackDate + "]";
	}

	

}
