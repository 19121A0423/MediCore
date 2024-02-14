package com.order.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.order.bean.Feedback;
import com.order.bean.Orders;
import com.order.bean.Product;
import com.order.controller.AddressController;
import com.order.entity.FeedbackEntity;
import com.order.entity.OrderEntity;
import com.order.exceptions.FeedbackNotFoundException;
import com.order.exceptions.OrderNotFoundException;
import com.order.exceptions.ProductNotFoundException;
import com.order.repository.FeedbackRepository;
import com.order.service.FeedbackService;
import com.order.service.OrderService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private OrderService orderService;

	private static Logger log = LoggerFactory.getLogger(FeedbackServiceImpl.class.getSimpleName());

	@Override
	public Feedback saveFeedback(Feedback feedback) {
		log.info("FeedbackServiceImpl::saveFeedback::Started");
		if (feedback.getUserId() == null || feedback.getOrder() == null || feedback.getFeedback() == null
				|| feedback.getRatings() == null) {
			throw new IllegalArgumentException("Feedback properties cannot be null");
		}

		FeedbackEntity feedbackEntity = new FeedbackEntity();
		beanToEntity(feedback, feedbackEntity);
		feedbackRepository.save(feedbackEntity);
		log.info("FeedbackServiceImpl::saveFeedback::Ended");
		entityToBean(feedback, feedbackEntity);
		return feedback;
	}

	@Override
	public Feedback getFeedbackById(int id) throws FeedbackNotFoundException {
		log.info("FeedbackServiceImpl::getFeedbackById::Started");
		FeedbackEntity feedbackEntity = feedbackRepository.findById(id)
				.orElseThrow(() -> new FeedbackNotFoundException("Address not found with ID: " + id));

		Feedback feedback = new Feedback();
		entityToBean(feedback, feedbackEntity);
		log.info("FeedbackServiceImpl::getFeedbackById::Ended");
		return feedback;
	}

	@Override
	public List<Feedback> getAllFeedbacks() throws FeedbackNotFoundException {
		log.info("FeedbackServiceImpl::getAllFeedbacks::Started");
		List<FeedbackEntity> feedbackEntities = feedbackRepository.findAll();
		if (feedbackEntities.isEmpty()) {
			throw new FeedbackNotFoundException("No feedbacks found");
		} else {
			List<Feedback> feedbacks = new ArrayList<>();
			entitiesToBeans(feedbacks, feedbackEntities);
			log.info("FeedbackServiceImpl::getAllFeedbacks::Ended");
			return feedbacks;
		}
	}

	@Override
	public void updateFeedbackById(int id, Feedback updatedFeedback) throws FeedbackNotFoundException {
		log.info("FeedbackServiceImpl::updateFeedbackById::Started");
		Optional<FeedbackEntity> optionalFeedbackEntity = feedbackRepository.findById(id);

		if (optionalFeedbackEntity.isPresent()) {
			FeedbackEntity feedbackEntity = optionalFeedbackEntity.get();
			feedbackEntity.setFeedbackId(id);
			beanToEntity(updatedFeedback, feedbackEntity);
			feedbackRepository.save(feedbackEntity);
		} else {
			throw new FeedbackNotFoundException("Feedback not found with ID: " + id);
		}
		log.info("FeedbackServiceImpl::updateFeedbackById::Ended");

	}

	@Override
	public void beanToEntity(Feedback feedback, FeedbackEntity feedbackEntity) {
		log.info("FeedbackServiceImpl::beanToEntity::Started");
		Orders order=null;
		try {
			order = orderService.getOrderById(feedback.getOrder().getOrderId());
			OrderEntity orderEntity = new OrderEntity();
			orderService.beanToEntity(order, orderEntity);
			feedbackEntity.setOrder(orderEntity);
		} catch (OrderNotFoundException e) {
			log.error("Order is not found with id "+feedback.getOrder().getOrderId());
		}
		feedbackEntity.setFeedbackId(feedback.getFeedbackId());
		feedbackEntity.setUserId(feedback.getUserId());
		feedbackEntity.setFeedback(feedback.getFeedback());
		feedbackEntity.setFeedbackDate(LocalDateTime.now());
		feedbackEntity.setRatings(feedback.getRatings());
		log.info("FeedbackServiceImpl::beanToEntity::Ended");
	}

	@Override
	public void entityToBean(Feedback feedback, FeedbackEntity feedbackEntity) {
		log.info("FeedbackServiceImpl::entityToBean::Started");
		feedback.setFeedbackId(feedbackEntity.getFeedbackId());
		Orders order = new Orders();
		orderService.entityToBean(order, feedbackEntity.getOrder());
		feedback.setOrder(order);
		feedback.setUserId(feedbackEntity.getUserId());
		feedback.setFeedback(feedbackEntity.getFeedback());
		feedback.setFeedbackDate(feedbackEntity.getFeedbackDate());
		feedback.setRatings(feedbackEntity.getRatings());
		log.info("FeedbackServiceImpl::entityToBean::Ended");
	}

	@Override
	public void entitiesToBeans(List<Feedback> feedbacks, List<FeedbackEntity> feedbackEntities) {
		log.info("FeedbackServiceImpl::entitiesToBeans::Started");
		feedbackEntities.stream().forEach(feedbackEntity -> {
			Feedback feedback = new Feedback();
			feedback.setFeedbackId(feedbackEntity.getFeedbackId());
			Orders order = new Orders();
			orderService.entityToBean(order, feedbackEntity.getOrder());
			feedback.setOrder(order);
			feedback.setUserId(feedbackEntity.getUserId());
			feedback.setFeedback(feedbackEntity.getFeedback());
			feedback.setFeedbackDate(feedbackEntity.getFeedbackDate());
			feedback.setRatings(feedbackEntity.getRatings());

			feedbacks.add(feedback);
		});
		log.info("FeedbackServiceImpl::entitiesToBeans::Ended");
	}

}
