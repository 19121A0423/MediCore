package com.order.service;

import java.util.List;

import com.order.bean.Feedback;
import com.order.bean.Product;
import com.order.exceptions.FeedbackNotFoundException;
import com.order.exceptions.ProductNotFoundException;


public interface FeedbackService {
	
	void saveFeedback(Feedback feedback);

	Feedback getFeedbackById(int id) throws FeedbackNotFoundException;

	List<Feedback> getAllFeedbacks() throws FeedbackNotFoundException;

	void updateFeedbackById(int id, Feedback updatedFeedback) throws FeedbackNotFoundException;

	Product getProduct(int id) throws ProductNotFoundException;
	
}
