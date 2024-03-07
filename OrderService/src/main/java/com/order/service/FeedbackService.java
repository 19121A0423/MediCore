package com.order.service;

import java.util.List;

import com.order.bean.FeedbackBean;
import com.order.exceptions.FeedbackNotFoundException;


public interface FeedbackService {
	
	FeedbackBean saveFeedback(FeedbackBean feedback);

	List<FeedbackBean> getAllFeedbacks() throws FeedbackNotFoundException;
	
}
