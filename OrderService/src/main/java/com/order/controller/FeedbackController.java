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
import com.order.service.FeedbackService;
import com.order.service.UserService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping("/save")
	public ResponseEntity<Feedback> save(@RequestBody Feedback feedback){
		feedbackService.save(feedback);
		return new ResponseEntity<Feedback>(feedback, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Feedback> updateFeedback(@RequestBody Feedback feedback, @PathVariable(value = "id") int id) {
	    try {
	    	feedbackService.updateById(id, feedback);
	        return new ResponseEntity<>(feedback, HttpStatus.OK);
	    } catch (FeedbackNotFoundException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Feedback> getFeedbackById(@PathVariable(value = "id") int id) {
	    try {
	    	Feedback feedback = feedbackService.findById(id);
	        return new ResponseEntity<>(feedback, HttpStatus.OK);
	    } catch (FeedbackNotFoundException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@GetMapping("/get/all")
	public ResponseEntity<List<Feedback>> getFeedbacks() {
	    List<Feedback> feedbacks = feedbackService.findAll();
	    return new ResponseEntity<>(feedbacks, HttpStatus.OK);	
	}
	
	@GetMapping("getProduct/{id}")
	public ResponseEntity<Product> getUser(@PathVariable(value="id") int id) {
	    Product product = feedbackService.getProduct(id);
	    return new ResponseEntity<>(product, HttpStatus.OK);	
	}
	
}
