package com.chamil.ShopMate.repository;

import com.chamil.ShopMate.model.orderEntity;
import com.chamil.ShopMate.model.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<orderEntity, Long> {

    public List<orderEntity> findByShopId(Long shopId);

    public List<orderEntity> findByShopOwnerId(Long userId);
}
