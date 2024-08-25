package com.chamil.ShopMate.repository;

import com.chamil.ShopMate.model.cartEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<cartEntity, Long> {

    public cartEntity findCartByShopOwnerId(Long userId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM cart_item_entity WHERE cart_id = :cartId",nativeQuery = true)
    public void clearCartByCartId(Long cartId);
}


