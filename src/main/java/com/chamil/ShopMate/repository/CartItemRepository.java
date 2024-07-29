package com.chamil.ShopMate.repository;

import com.chamil.ShopMate.model.cartEntity;
import com.chamil.ShopMate.model.cartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<cartItemEntity, Long> {

}
