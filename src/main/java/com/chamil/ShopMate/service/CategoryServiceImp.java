package com.chamil.ShopMate.service;

import com.chamil.ShopMate.model.categoryEntity;
import com.chamil.ShopMate.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public categoryEntity createCategory(String name) {
        categoryEntity category = new categoryEntity();
        category.setName(name);
        return categoryRepository.save(category);
    }

    @Override
    public categoryEntity updateCategory(Long categoryId, String name) throws Exception {
        categoryEntity category = findCategoryById(categoryId);
        category.setName(name);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) throws Exception {
        categoryEntity category = findCategoryById(categoryId);
        categoryRepository.delete(category);
    }

    @Override
    public categoryEntity findCategoryById(Long categoryId) throws Exception {
        Optional<categoryEntity> optionalCategoryEntity = categoryRepository.findById(categoryId);

        if(optionalCategoryEntity.isEmpty()){
            throw new Exception("Category not exist");
        }
        return optionalCategoryEntity.get();
    }

    @Override
    public List<categoryEntity> searchCategory(String keyword) {
        return categoryRepository.searchCategory(keyword);
    }
}
