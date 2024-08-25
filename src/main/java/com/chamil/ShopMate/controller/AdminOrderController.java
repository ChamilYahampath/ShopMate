package com.chamil.ShopMate.controller;

import com.chamil.ShopMate.dto.ResponseDTO;
import com.chamil.ShopMate.model.orderEntity;
import com.chamil.ShopMate.model.userEntity;
import com.chamil.ShopMate.service.OrderService;
import com.chamil.ShopMate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/order/shop/{id}")
    public ResponseEntity<List<orderEntity>> getOrderHistory(
            @PathVariable Long id,
            @RequestParam(required = false) String order_status,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);
        List<orderEntity> orders = orderService.getShopsOrder(id, order_status);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/order/{id}/{order_status}")
    public ResponseEntity<orderEntity> updateOrderStatus(
            @PathVariable Long id,
            @PathVariable String order_status,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);
        orderEntity orders = orderService.updateOrder(id, order_status);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/order")
    public ResponseEntity<List<orderEntity>> getAllOrders(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);
        List<orderEntity> orders = orderService.getAllOrders();

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
