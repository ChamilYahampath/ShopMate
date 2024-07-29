package com.chamil.ShopMate.dto;

import com.chamil.ShopMate.model.cartItemEntity;
import com.chamil.ShopMate.model.userEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class CartDTO {
    private Long id;

    private double totalPrice;

    private int totalItems;

    private List<CartItemDTO> items;
}
