package com.order.entity;

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
@Table(name="feedback")
public class FeedbackEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="feedback_id")
	private Integer feedbackId;
	
	@Column(name="user_id")
	private Integer userId;
	
	@OneToOne
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	@JsonIgnore
	private OrderEntity order;
	
	@Column(name="feedback")
	private String feedback;
	
	@Column(name="ratings")
	private Double ratings;
	
	@Column(name="feedback_date")
	private LocalDate feedbackDate;

	public FeedbackEntity() {
		super();
	}

	public FeedbackEntity(Integer feedbackId, Integer userId, OrderEntity order, String feedback, Double ratings,
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

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
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

	@Override
	public String toString() {
		return "FeedbackEntity [feedbackId=" + feedbackId + ", userId=" + userId + ", order=" + order + ", feedback="
				+ feedback + ", ratings=" + ratings + ", feedbackDate=" + feedbackDate + "]";
	}

	

}
