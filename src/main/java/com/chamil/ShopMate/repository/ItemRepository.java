package com.chamil.ShopMate.repository;

import com.chamil.ShopMate.model.itemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<itemEntity, Long> {

    @Query("SELECT f FROM itemEntity f WHERE f.name LIKE %:keyword% OR f.itemCategory.name LIKE %:keyword%")
    List<itemEntity> searchItem(@Param("keyword") String keyword);

}
