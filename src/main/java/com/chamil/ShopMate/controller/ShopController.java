package com.chamil.ShopMate.controller;

import com.chamil.ShopMate.model.shopEntity;
import com.chamil.ShopMate.model.userEntity;
import com.chamil.ShopMate.service.ShopService;
import com.chamil.ShopMate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<shopEntity>> searchShop(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String keyword
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);

        List<shopEntity> shop = shopService.searchShops(keyword);
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<shopEntity>> getAllShop(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);

        List<shopEntity> shop = shopService.getAllShops();
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<shopEntity> findRestaurantById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);

        shopEntity shop = shopService.findShopById(id);
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }
}
