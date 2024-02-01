package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.bean.Feedback;
import com.order.bean.Product;
import com.order.exceptions.FeedbackNotFoundException;
import com.order.exceptions.ProductNotFoundException;
import com.order.service.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping("/save")
	public ResponseEntity<Feedback> saveFeedback(@RequestBody Feedback feedback){
		try {
			feedbackService.saveFeedback(feedback);
			return new ResponseEntity<Feedback>(feedback, HttpStatus.CREATED);
		}
		catch(IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Feedback> updateFeedbackById(@RequestBody Feedback feedback, @PathVariable(value = "id") int id) {
	    try {
	    	feedbackService.updateFeedbackById(id, feedback);
	        return new ResponseEntity<>(feedback, HttpStatus.OK);
	    } catch (FeedbackNotFoundException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Feedback> getFeedbackById(@PathVariable(value = "id") int id) {
	    try {
	    	Feedback feedback = feedbackService.getFeedbackById(id);
	        return new ResponseEntity<>(feedback, HttpStatus.OK);
	    } catch (FeedbackNotFoundException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@GetMapping("/get/all")
	public ResponseEntity<List<Feedback>> getAllFeedbacks() {
	    List<Feedback> feedbacks;
		try {
			feedbacks = feedbackService.getAllFeedbacks();
			return new ResponseEntity<>(feedbacks, HttpStatus.OK);
		} catch (FeedbackNotFoundException e) {
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}
	    	
	}
	
	@GetMapping("getProduct/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable(value="id") int id) {
	    Product product;
		try {
			product = feedbackService.getProduct(id);
			return new ResponseEntity<>(product, HttpStatus.FOUND);
		} catch (ProductNotFoundException e) {
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}
	    	
	}
	
}
