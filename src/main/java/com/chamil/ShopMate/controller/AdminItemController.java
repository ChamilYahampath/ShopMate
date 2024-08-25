package com.chamil.ShopMate.controller;

import com.chamil.ShopMate.dto.ResponseDTO;
import com.chamil.ShopMate.model.itemEntity;
import com.chamil.ShopMate.model.userEntity;
import com.chamil.ShopMate.request.CreateItemRequest;
import com.chamil.ShopMate.response.MessageResponse;
import com.chamil.ShopMate.service.ItemService;
import com.chamil.ShopMate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/item")
public class AdminItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<itemEntity> createItem(
            @RequestBody CreateItemRequest req,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        //userEntity user = userService.findUserByJwtToken(jwt);

        ResponseDTO responseDTO = itemService.createItem(req, req.getCategory());

        return new ResponseEntity(responseDTO,responseDTO.getStatus());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<itemEntity> updateItem(
            @PathVariable Long id,
            @RequestBody CreateItemRequest req,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);

        itemEntity item = itemService.updateItem(id, req);

        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteItem(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);

        itemService.deleteItem(id);

        MessageResponse res = new MessageResponse();
        res.setMessage("Item deleted successfully");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<itemEntity> updateItemAvailabilityStatus(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);

        itemEntity item = itemService.updateAvailabilityStatus(id);

        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }
}
