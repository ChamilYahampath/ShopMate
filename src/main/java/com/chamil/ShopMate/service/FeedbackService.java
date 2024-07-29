package com.chamil.ShopMate.service;

import com.chamil.ShopMate.model.feedbackEntity;
import com.chamil.ShopMate.model.orderEntity;
import com.chamil.ShopMate.request.CreateFeedbackRequest;

import java.util.List;

public interface FeedbackService {

    public feedbackEntity createFeedback(CreateFeedbackRequest req, Long orderId);

    public List<feedbackEntity> searchFeedback(String keyword);

    feedbackEntity findFeedbackByOrderId(Long orderId) throws Exception;

    List<feedbackEntity> getAllFeedbacks();
}
