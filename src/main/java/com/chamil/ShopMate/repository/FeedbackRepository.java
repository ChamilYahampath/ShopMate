package com.chamil.ShopMate.repository;

import com.chamil.ShopMate.model.feedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<feedbackEntity, Long> {

}
