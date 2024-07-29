package com.chamil.ShopMate.controller;

import com.chamil.ShopMate.dto.ResponseDTO;
import com.chamil.ShopMate.model.shopEntity;
import com.chamil.ShopMate.model.userEntity;
import com.chamil.ShopMate.request.CreateShopRequest;
import com.chamil.ShopMate.response.MessageResponse;
import com.chamil.ShopMate.service.ShopService;
import com.chamil.ShopMate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<shopEntity> createShop(
            @RequestBody CreateShopRequest req,
            @RequestHeader ("Authorization") String jwt
            ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);

        shopEntity shop = shopService.createShop(req, user);
        return new ResponseEntity<>(shop, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<shopEntity> updateShop(
            @RequestBody CreateShopRequest req,
            @RequestHeader ("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);

        shopEntity shop = shopService.updateShop(id, req);
        return new ResponseEntity<>(shop, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteShop(
            @RequestHeader ("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);

        shopService.deleteShop(id);

        MessageResponse res = new MessageResponse();
        res.setMessage("Shop deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<shopEntity> updateShopStatus(
            @RequestHeader ("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);

        shopEntity shop = shopService.updateShopStatus(id);

        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<shopEntity> findShopByUserId(
            @RequestHeader ("Authorization") String jwt
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);

        ResponseDTO responseDTO = shopService.getShopByUserId(user.getId());

        return new ResponseEntity(responseDTO,responseDTO.getStatus());
    }
}
