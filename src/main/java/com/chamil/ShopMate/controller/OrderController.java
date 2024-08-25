package com.chamil.ShopMate.controller;

import com.chamil.ShopMate.dto.ResponseDTO;
import com.chamil.ShopMate.model.orderEntity;
import com.chamil.ShopMate.model.userEntity;
import com.chamil.ShopMate.request.OrderRequest;
import com.chamil.ShopMate.service.OrderService;
import com.chamil.ShopMate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<orderEntity> createOrder(
            @RequestBody OrderRequest req,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);
        ResponseDTO responseDTO = orderService.createOrder(req, user);

        return new ResponseEntity(responseDTO,responseDTO.getStatus());
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<orderEntity>> getOrderHistory(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);
        List<orderEntity> orders = orderService.getUsersOrder(user.getId());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
