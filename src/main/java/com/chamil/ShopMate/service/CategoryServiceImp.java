package com.chamil.ShopMate.service;

import com.chamil.ShopMate.dto.CategoryDTO;
import com.chamil.ShopMate.dto.ResponseDTO;
import com.chamil.ShopMate.model.categoryEntity;
import com.chamil.ShopMate.repository.CategoryRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseDTO createCategory(String name) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            if (categoryRepository.countOfCategory(name.toLowerCase()) == 0) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryEntity category = new categoryEntity();
                category.setName(name.toLowerCase());
                categoryRepository.save(category);
                responseDTO.setCode("00");
                responseDTO.setMessage("Saved Successfully");
                responseDTO.setStatus(HttpStatus.ACCEPTED);

            } else {
                responseDTO.setCode("06");
                responseDTO.setMessage("Repeated");
                responseDTO.setStatus(HttpStatus.MULTI_STATUS);
            }

        } catch (Exception e) {
            responseDTO.setCode("10");
            responseDTO.setContent("Internal server Error");
            responseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return responseDTO;
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

    @Override
    public List<categoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }
}
