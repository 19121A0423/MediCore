package com.order.service;

import java.util.List;

import com.order.bean.Feedback;
import com.order.exceptions.FeedbackNotFoundException;


public interface FeedbackService {
	
	void save(Feedback feedback);

	Feedback findById(int id) throws FeedbackNotFoundException;

	List<Feedback> findAll();

	void updateById(int id, Feedback updatedFeedback) throws FeedbackNotFoundException;
	
}
