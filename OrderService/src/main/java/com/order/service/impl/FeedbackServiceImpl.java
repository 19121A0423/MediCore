package com.order.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.order.bean.Product;
import com.order.entity.FeedbackEntity;
import com.order.exceptions.FeedbackNotFoundException;
import com.order.exceptions.ProductNotFoundException;
import com.order.repository.FeedbackRepository;
import com.order.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void saveFeedback(Feedback feedback) {
		if (feedback.getUserId()==null || feedback.getProductId()==null || feedback.getFeedback()==null || 
				feedback.getRatings() == null) {
			throw new IllegalArgumentException("Feedback properties cannot be null");
		}

		FeedbackEntity feedbackEntity = new FeedbackEntity();
		beanToEntity(feedback, feedbackEntity);
		feedbackRepository.save(feedbackEntity);

	}

	@Override
	public Feedback getFeedbackById(int id) throws FeedbackNotFoundException {
		FeedbackEntity feedbackEntity = feedbackRepository.findById(id)
				.orElseThrow(() -> new FeedbackNotFoundException("Address not found with ID: " + id));

		Feedback feedback = new Feedback();
		entityToBean(feedback, feedbackEntity);
		return feedback;
	}

	@Override
	public List<Feedback> getAllFeedbacks() throws FeedbackNotFoundException {
		List<FeedbackEntity> feedbackEntities = feedbackRepository.findAll();
		if(feedbackEntities.isEmpty()) {
			throw new FeedbackNotFoundException("No feedbacks found");
		}
		else {
			List<Feedback> feedbacks = new ArrayList<>();
			entitiesToBeans(feedbacks, feedbackEntities);
			return feedbacks;
		}
	}

	@Override
	public void updateFeedbackById(int id, Feedback updatedFeedback) throws FeedbackNotFoundException {
		Optional<FeedbackEntity> optionalFeedbackEntity = feedbackRepository.findById(id);

		if (optionalFeedbackEntity.isPresent()) {
			FeedbackEntity feedbackEntity = optionalFeedbackEntity.get();
			feedbackEntity.setFeedbackId(id);
			beanToEntity(updatedFeedback, feedbackEntity);
			feedbackRepository.save(feedbackEntity);
		} else {
			throw new FeedbackNotFoundException("Feedback not found with ID: " + id);
		}

	}

	@Override
	public Product getProduct(int id) throws ProductNotFoundException {
		String url = "http://localhost:8082/medicine/productController/" + id;

		ParameterizedTypeReference<Product> responseType = new ParameterizedTypeReference<Product>() {
		};

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<Product> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
				httpEntity, responseType);
		Product product = responseEntity.getBody();
		if(product==null) {
			throw new ProductNotFoundException("Product not found with id : "+id);
		}else {
		return product;
		}
	}

	public void beanToEntity(Feedback feedback, FeedbackEntity feedbackEntity) {
		feedbackEntity.setProductId(feedback.getProductId());
		feedbackEntity.setUserId(feedback.getUserId());
		feedbackEntity.setFeedback(feedback.getFeedback());
		feedbackEntity.setFeedbackDate(LocalDate.now());
		feedbackEntity.setRatings(feedback.getRatings());
	}

	public void entityToBean(Feedback feedback, FeedbackEntity feedbackEntity) {
		feedback.setFeedbackId(feedbackEntity.getFeedbackId());
		feedback.setProductId(feedbackEntity.getProductId());
		feedback.setUserId(feedbackEntity.getUserId());
		feedback.setFeedback(feedbackEntity.getFeedback());
		feedback.setFeedbackDate(feedbackEntity.getFeedbackDate());
		feedback.setRatings(feedbackEntity.getRatings());
	}

	public void entitiesToBeans(List<Feedback> feedbacks, List<FeedbackEntity> feedbackEntities) {

		feedbackEntities.stream().forEach(feedbackEntity -> {
			Feedback feedback = new Feedback();
			feedback.setFeedbackId(feedbackEntity.getFeedbackId());
			feedback.setProductId(feedbackEntity.getProductId());
			feedback.setUserId(feedbackEntity.getUserId());
			feedback.setFeedback(feedbackEntity.getFeedback());
			feedback.setFeedbackDate(feedbackEntity.getFeedbackDate());
			feedback.setRatings(feedbackEntity.getRatings());

			feedbacks.add(feedback);
		});
	}

}
