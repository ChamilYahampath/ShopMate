package com.chamil.ShopMate.controller;

import com.chamil.ShopMate.model.feedbackEntity;
import com.chamil.ShopMate.service.FeedbackService;
import com.chamil.ShopMate.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/feedback")
public class AdminFeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<feedbackEntity>> getAllFeedbacks(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        //userEntity user = userService.findUserByJwtToken(jwt);

        List<feedbackEntity> feedbacks = feedbackService.getAllFeedbacks();

        return new ResponseEntity<>(feedbacks, HttpStatus.CREATED);
    }
}
