package com.order.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.order.bean.FeedbackBean;
import com.order.controller.FeedbackController;
import com.order.exceptions.FeedbackNotFoundException;
import com.order.service.FeedbackService;

@ExtendWith(MockitoExtension.class)
class FeedbackControllerTest {

	@Mock
	private FeedbackService feedbackService;

	@InjectMocks
	private FeedbackController feedbackController;

	@Test
	void testSaveFeedback() {

		FeedbackBean feedbackBean = new FeedbackBean();
		feedbackBean.setFeedbackId(1);
		feedbackBean.setUserId(1);
		feedbackBean
				.setFeedback("I really enjoyed. It was easy to find what I needed and the checkout process was smooth");
		feedbackBean.setRatings(4.0);
		feedbackBean.setFeedbackDate(LocalDateTime.now());

		when(feedbackService.saveFeedback(any(FeedbackBean.class))).thenReturn(feedbackBean);

		ResponseEntity<FeedbackBean> response = feedbackController.saveFeedback(feedbackBean);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(feedbackBean, response.getBody());

	}

	@Test
	void testGetAllFeedbacks() throws FeedbackNotFoundException {

		FeedbackBean feedback1 = new FeedbackBean();
		feedback1.setFeedbackId(3);
		feedback1.setUserId(1);
		feedback1.setFeedback(
				"Great job on the performance! Products loaded quickly, and I didn't encounter any errors.");
		feedback1.setRatings(5.0);
		feedback1.setFeedbackDate(LocalDateTime.now());

		FeedbackBean feedback2 = new FeedbackBean();
		feedback2.setFeedbackId(8);
		feedback2.setUserId(2);
		feedback2
				.setFeedback("I really enjoyed. It was easy to find what I needed and the checkout process was smooth");
		feedback2.setRatings(4.0);
		feedback2.setFeedbackDate(LocalDateTime.parse("2024-03-06T20:27:12.249236"));

		List<FeedbackBean> feedbacks = new ArrayList<>();
		feedbacks.add(feedback1);
		feedbacks.add(feedback2);

		when(feedbackService.getAllFeedbacks()).thenReturn(feedbacks);

		ResponseEntity<List<FeedbackBean>> responseEntity = feedbackController.getAllFeedbacks();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(feedbacks, responseEntity.getBody());
	}

}
