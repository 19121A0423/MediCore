package com.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static Logger log = LoggerFactory.getLogger(FeedbackController.class.getSimpleName());
	
	@PostMapping("/save")
	public ResponseEntity<Feedback> saveFeedback(@RequestBody Feedback feedback){
		log.info("FeedbackController::saveFeedback::Started");
		log.info("Feedback : "+feedback);
		try {
			feedbackService.saveFeedback(feedback);
			log.info("FeedbackController::saveFeedback::Ended");
			return new ResponseEntity<Feedback>(feedback, HttpStatus.CREATED);
		}
		catch(IllegalArgumentException e) {
			log.error("FeedbackController::saveFeedback::"+e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Feedback> updateFeedbackById(@RequestBody Feedback feedback, @PathVariable(value = "id") int id) {
		log.info("FeedbackController::updateFeedbackById::Started");
		log.info("FeedbcakId : "+id+"Feedback : "+feedback);
		try {
	    	feedbackService.updateFeedbackById(id, feedback);
	    	log.info("FeedbackController::updateFeedbackById::Ended");
	        return new ResponseEntity<>(feedback, HttpStatus.OK);
	    } catch (FeedbackNotFoundException e) {
	    	log.error("FeedbackController::updateFeedbackById::"+e.getMessage());
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Feedback> getFeedbackById(@PathVariable(value = "id") int id) {
		log.info("FeedbackController::getFeedbackById::Started");
		log.info("FeedbackId : "+id);
		try {
	    	Feedback feedback = feedbackService.getFeedbackById(id);
	    	log.info("FeedbackController::getFeedbackById::Ended");
	        return new ResponseEntity<>(feedback, HttpStatus.OK);
	    } catch (FeedbackNotFoundException e) {
	    	log.error("FeedbackController::getFeedbackById::"+e.getMessage());
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@GetMapping("/get/all")
	public ResponseEntity<List<Feedback>> getAllFeedbacks() {
		log.info("FeedbackController::getAllFeedbacks::Started");
		List<Feedback> feedbacks;
		try {
			feedbacks = feedbackService.getAllFeedbacks();
			log.info("FeedbackController::getAllFeedbacks::Ended");
			return new ResponseEntity<>(feedbacks, HttpStatus.OK);
		} catch (FeedbackNotFoundException e) {
			log.error("FeedbackController::getAllFeedbacks::"+e.getMessage());
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}
	    	
	}
	

	
}
