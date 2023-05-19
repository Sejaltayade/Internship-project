package com.controller;
import com.dao.FeedbackRepository;
import com.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@Valid @RequestBody Feedback feedback) {
        Feedback createdFeedback = feedbackRepository.save(feedback);
        return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Feedback>> getAllFeedback(Pageable pageable) {
        Page<Feedback> feedbackPage = feedbackRepository.findAll(pageable);
        return new ResponseEntity<>(feedbackPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable("id") Long id) {
        Optional<Feedback> feedbackOptional = feedbackRepository.findById(id);
        if (feedbackOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Feedback feedback = feedbackOptional.get();
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable("id") Long id, @Valid @RequestBody Feedback updatedFeedback) {
        Optional<Feedback> feedbackOptional = feedbackRepository.findById(id);
        if (feedbackOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Feedback feedback = feedbackOptional.get();

        feedback.setName(updatedFeedback.getName());
        feedback.setEmail(updatedFeedback.getEmail());
        feedback.setRating(updatedFeedback.getRating());
        feedback.setComments(updatedFeedback.getComments());
        feedback.setUpdatedAt(new Date());
        feedback.setResponse(updatedFeedback.getResponse());

        Feedback updatedFeedbackEntity = feedbackRepository.save(feedback);
        return new ResponseEntity<>(updatedFeedbackEntity, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        String errorMessage = "An error occurred: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
