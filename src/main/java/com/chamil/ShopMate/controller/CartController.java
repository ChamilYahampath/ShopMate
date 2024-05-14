package com.chamil.ShopMate.controller;

import com.chamil.ShopMate.model.cartEntity;
import com.chamil.ShopMate.model.cartItemEntity;
import com.chamil.ShopMate.model.userEntity;
import com.chamil.ShopMate.request.AddCartItemRequest;
import com.chamil.ShopMate.request.UpdateCartItemRequest;
import com.chamil.ShopMate.service.CartService;
import com.chamil.ShopMate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PutMapping("/cart/add")
    public ResponseEntity<cartItemEntity> addItemToCart(
            @RequestBody AddCartItemRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {
        cartItemEntity cartItem = cartService.addItemToCart(req, jwt);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<cartItemEntity> updateCartItemQuantity(
            @RequestBody UpdateCartItemRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {
        cartItemEntity cartItem = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<cartEntity> removeCartItem(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {
        cartEntity cart = cartService.removeCartItem(id, jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<cartEntity> clearCart(
            @RequestHeader("Authorization") String jwt) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);
        cartEntity cart = cartService.clearCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<cartEntity> findUserCart(
            @RequestHeader("Authorization") String jwt) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);
        cartEntity cart = cartService.findCartByUserId(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
