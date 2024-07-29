package com.chamil.ShopMate.controller;

import com.chamil.ShopMate.model.itemEntity;
import com.chamil.ShopMate.model.userEntity;
import com.chamil.ShopMate.request.CreateItemRequest;
import com.chamil.ShopMate.service.ItemService;
import com.chamil.ShopMate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<itemEntity>> searchItem(
            @RequestParam String keyword,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);

        List<itemEntity> items = itemService.searchItem(keyword);

        return new ResponseEntity<>(items, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<itemEntity>> getAllItems(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);

        List<itemEntity> items = itemService.getAllItems();

        return new ResponseEntity<>(items, HttpStatus.CREATED);
    }
}
