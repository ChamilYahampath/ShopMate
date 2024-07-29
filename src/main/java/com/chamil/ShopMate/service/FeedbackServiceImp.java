package com.chamil.ShopMate.service;

import com.chamil.ShopMate.model.feedbackEntity;
import com.chamil.ShopMate.model.orderEntity;
import com.chamil.ShopMate.repository.FeedbackRepository;
import com.chamil.ShopMate.repository.OrderRepository;
import com.chamil.ShopMate.request.CreateFeedbackRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImp implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public feedbackEntity createFeedback(CreateFeedbackRequest req, Long orderId) {
        System.out.println("Create Feedback" + req.getName());
        feedbackEntity feedback = new feedbackEntity();
        feedback.setName(req.getName());
        feedback.setFeedbackDate(LocalDateTime.now());
        feedback.setDescription(req.getDescription());
        feedback.setRating(req.getRating());
        feedback.setOrderId(orderId);
        feedback.setDeliverDate(req.getDeliverDate());

        Optional<orderEntity> optionalOrder = orderRepository.findById(orderId);
        //order = orderService.findOrderById(orderId);

        feedback.setOrderDate(optionalOrder.get().getOrderDate());
        feedback.setImages(req.getImages());

        return feedbackRepository.save(feedback);
    }

    @Override
    public List<feedbackEntity> searchFeedback(String keyword) {
        return null;
    }


    @Override
    public feedbackEntity findFeedbackByOrderId(Long orderId) throws Exception {
        return null;
    }

    @Override
    public List<feedbackEntity> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }
}
