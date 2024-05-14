package com.chamil.ShopMate.repository;

import com.chamil.ShopMate.model.orderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<orderEntity, Long> {

    public List<orderEntity> findByUserId(Long userId);

    public List<orderEntity> findByShopId(Long shopId);

}
