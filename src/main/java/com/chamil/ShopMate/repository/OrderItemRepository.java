package com.chamil.ShopMate.repository;

import com.chamil.ShopMate.model.orderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<orderItemEntity, Long> {

}
