package com.chamil.ShopMate.service;

import com.chamil.ShopMate.model.categoryEntity;

import java.util.List;

public interface CategoryService {

    public categoryEntity createCategory(String name);

    public categoryEntity updateCategory(Long categoryId, String name) throws Exception;

    public void deleteCategory(Long categoryId) throws Exception;

    public categoryEntity findCategoryById(Long categoryId) throws Exception;

    List<categoryEntity> searchCategory(String keyword);
}
