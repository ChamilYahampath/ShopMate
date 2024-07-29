package com.chamil.ShopMate.repository;

import com.chamil.ShopMate.model.categoryEntity;
import com.chamil.ShopMate.model.itemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<categoryEntity, Long> {

    @Query("SELECT f FROM categoryEntity f WHERE f.name LIKE %:keyword%")
    List<categoryEntity> searchCategory(@Param("keyword") String keyword);

    @Query(value = "SELECT count(*) FROM category_entity WHERE name =:categoryName", nativeQuery = true)
    int countOfCategory(@PathVariable("categoryName") String categoryName);

    public categoryEntity findByName(String name);
}
