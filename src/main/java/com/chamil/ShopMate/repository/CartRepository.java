package com.chamil.ShopMate.repository;

import com.chamil.ShopMate.model.cartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<cartEntity, Long> {

    public cartEntity findCartByUserId(Long userId);
}


