package com.chamil.ShopMate.service;

import com.chamil.ShopMate.dto.ResponseDTO;
import com.chamil.ShopMate.model.categoryEntity;

import java.util.List;

public interface CategoryService {

    public ResponseDTO createCategory(String name);

    public categoryEntity updateCategory(Long categoryId, String name) throws Exception;

    public void deleteCategory(Long categoryId) throws Exception;

    public categoryEntity findCategoryById(Long categoryId) throws Exception;

    List<categoryEntity> searchCategory(String keyword);

    List<categoryEntity> getAllCategories();
}
