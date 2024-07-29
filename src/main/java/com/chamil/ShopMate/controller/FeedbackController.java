package com.chamil.ShopMate.controller;

import com.chamil.ShopMate.model.feedbackEntity;
import com.chamil.ShopMate.model.itemEntity;
import com.chamil.ShopMate.model.userEntity;
import com.chamil.ShopMate.request.CreateFeedbackRequest;
import com.chamil.ShopMate.request.CreateItemRequest;
import com.chamil.ShopMate.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<feedbackEntity> createFeedback(
            @RequestBody CreateFeedbackRequest req,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        feedbackEntity feedback = feedbackService.createFeedback(req, req.getOrderId());

        return new ResponseEntity<>(feedback, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<feedbackEntity>> searchFeedback(
            @RequestParam String keyword,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
       // userEntity user = userService.findUserByJwtToken(jwt);

        List<feedbackEntity> feedback = feedbackService.searchFeedback(keyword);

        return new ResponseEntity<>(feedback, HttpStatus.CREATED);
    }

}
