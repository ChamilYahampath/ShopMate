package com.chamil.ShopMate.repository;

import com.chamil.ShopMate.model.feedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<feedbackEntity, String> {


}
