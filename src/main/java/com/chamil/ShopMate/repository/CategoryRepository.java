package com.chamil.ShopMate.repository;

import com.chamil.ShopMate.model.categoryEntity;
import com.chamil.ShopMate.model.itemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<categoryEntity, Long> {

    @Query("SELECT f FROM categoryEntity f WHERE f.name LIKE %:keyword%")
    List<categoryEntity> searchCategory(@Param("keyword") String keyword);

    public categoryEntity findByName(String name);
}
