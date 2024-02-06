package com.order.service;

import java.util.List;

import com.order.bean.Feedback;
import com.order.bean.Product;
import com.order.entity.FeedbackEntity;
import com.order.exceptions.FeedbackNotFoundException;
import com.order.exceptions.ProductNotFoundException;


public interface FeedbackService {
	
	void saveFeedback(Feedback feedback);

	Feedback getFeedbackById(int id) throws FeedbackNotFoundException;

	List<Feedback> getAllFeedbacks() throws FeedbackNotFoundException;

	void updateFeedbackById(int id, Feedback updatedFeedback) throws FeedbackNotFoundException;

	void beanToEntity(Feedback feedback, FeedbackEntity feedbackEntity);

	void entityToBean(Feedback feedback, FeedbackEntity feedbackEntity);

	void entitiesToBeans(List<Feedback> feedbacks, List<FeedbackEntity> feedbackEntities);
	
}
