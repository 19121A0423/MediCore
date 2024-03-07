package com.order.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.bean.FeedbackBean;
import com.order.bean.OrderBean;
import com.order.entity.Feedback;
import com.order.entity.Orders;
import com.order.exceptions.FeedbackNotFoundException;
import com.order.exceptions.OrderNotFoundException;
import com.order.repository.FeedbackRepository;
import com.order.service.FeedbackService;
import com.order.service.OrderService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ObjectMapper mapper;

	private static Logger log = LoggerFactory.getLogger(FeedbackServiceImpl.class.getSimpleName());

	@Override
	public FeedbackBean saveFeedback(FeedbackBean feedbackBean) {
		log.info("FeedbackServiceImpl::saveFeedback::Started");
		if (feedbackBean.getUserId() <= 0 || feedbackBean.getFeedback().isEmpty()
				|| feedbackBean.getRatings() <=0) {
			throw new IllegalArgumentException("Feedback properties cannot be null");
		}
		else {
			Feedback feedback = new Feedback();
			feedback = beanToEntity(feedbackBean, feedback);
			feedbackRepository.save(feedback);
			log.info("FeedbackServiceImpl::saveFeedback::Ended");
			return feedbackBean;
		}
	}

	@Override
	public List<FeedbackBean> getAllFeedbacks() throws FeedbackNotFoundException {
		log.info("FeedbackServiceImpl::getAllFeedbacks::Started");
		List<Feedback> feedbackEntities = feedbackRepository.findAll();
		if (feedbackEntities.isEmpty()) {
			throw new FeedbackNotFoundException("No feedbacks found");
		} else {
			List<FeedbackBean> feedbacks = new ArrayList<>();
			entitiesToBeans(feedbacks, feedbackEntities);
			log.info("FeedbackServiceImpl::getAllFeedbacks::Ended");
			return feedbacks;
		}
	}

	public Feedback beanToEntity(FeedbackBean feedbackBean, Feedback feedback) {
		log.info("FeedbackServiceImpl::beanToEntity::Started");
		feedback = mapper.convertValue(feedbackBean, Feedback.class);
		OrderBean orderBean=null;
		try {
			orderBean = orderService.getOrderById(feedbackBean.getOrder().getOrderId());
			Orders order = new Orders();
			order = mapper.convertValue(order, Orders.class);
			feedback.setOrder(order);
		} catch (OrderNotFoundException e) {
			log.error("Order is not found with id "+feedbackBean.getOrder().getOrderId());
		}
		feedback.setFeedbackDate(LocalDateTime.now());
		log.info("FeedbackServiceImpl::beanToEntity::Ended");
		return feedback;
	}

	public void entitiesToBeans(List<FeedbackBean> feedbackBeans, List<Feedback> feedbacks) {
		log.info("FeedbackServiceImpl::entitiesToBeans::Started");
		feedbacks.stream().forEach(feedback -> {
			FeedbackBean feedbackBean = new FeedbackBean();
			feedbackBean.setFeedbackId(feedback.getFeedbackId());
			OrderBean orderBean = new OrderBean();
			orderBean = mapper.convertValue(feedback.getOrder(), OrderBean.class);
			feedbackBean.setOrder(orderBean);
			feedbackBean.setUserId(feedback.getUserId());
			feedbackBean.setFeedback(feedback.getFeedback());
			feedbackBean.setFeedbackDate(feedback.getFeedbackDate());
			feedbackBean.setRatings(feedback.getRatings());

			feedbackBeans.add(feedbackBean);
		});
		log.info("FeedbackServiceImpl::entitiesToBeans::Ended");
	}

}
