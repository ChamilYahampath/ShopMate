package com.chamil.ShopMate.repository;

import com.chamil.ShopMate.model.shopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopRepository extends JpaRepository<shopEntity, Long> {

    @Query("SELECT r FROM shopEntity r WHERE lower(r.name) LIKE lower(concat('%', :query, '%')) OR lower(r.description) LIKE lower(concat('%', :query, '%'))")
    List<shopEntity> findBySearchQuery(String query);
    shopEntity findByOwnerId(Long userId);
}
