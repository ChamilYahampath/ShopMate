package com.chamil.ShopMate.controller;

import com.chamil.ShopMate.dto.ResponseDTO;
import com.chamil.ShopMate.model.categoryEntity;
import com.chamil.ShopMate.model.itemEntity;
import com.chamil.ShopMate.model.userEntity;
import com.chamil.ShopMate.response.MessageResponse;
import com.chamil.ShopMate.service.CategoryService;
import com.chamil.ShopMate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<categoryEntity> createCategory(
            @RequestBody categoryEntity category,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        //userEntity user = userService.findUserByJwtToken(jwt);

        ResponseDTO responseDTO = categoryService.createCategory(category.getName());

        return new ResponseEntity(responseDTO,responseDTO.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteCategory(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        //userEntity user = userService.findUserByJwtToken(jwt);

        categoryService.deleteCategory(id);

        MessageResponse res = new MessageResponse();
        res.setMessage("Category deleted successfully");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<categoryEntity> updateCategory(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);

        categoryEntity category = categoryService.updateCategory(id,user.getName()); // is id needed?

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<categoryEntity>> searchCategory(
            @RequestParam String keyword,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);

        List<categoryEntity> categories = categoryService.searchCategory(keyword);

        return new ResponseEntity<>(categories, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<categoryEntity>> getAllCategories(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);

        List<categoryEntity> categories = categoryService.getAllCategories();

        return new ResponseEntity<>(categories, HttpStatus.CREATED);
    }
}
