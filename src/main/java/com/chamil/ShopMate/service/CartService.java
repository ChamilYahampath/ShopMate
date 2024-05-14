package com.chamil.ShopMate.service;

import com.chamil.ShopMate.model.cartEntity;
import com.chamil.ShopMate.model.cartItemEntity;
import com.chamil.ShopMate.request.AddCartItemRequest;

public interface CartService {

    public cartItemEntity addItemToCart(AddCartItemRequest req, String jwt) throws Exception;

    public cartItemEntity updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    public cartEntity removeCartItem(Long cartItemId, String jwt) throws Exception;

    public Long calculateCartTotal(cartEntity cart) throws Exception;

    public cartEntity findCartById(Long id) throws Exception;

    public cartEntity findCartByUserId(Long userId) throws Exception;

    public cartEntity clearCart(Long userId) throws Exception;
}
