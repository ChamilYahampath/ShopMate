package com.chamil.ShopMate.dto;

import com.chamil.ShopMate.model.cartEntity;
import com.chamil.ShopMate.model.itemEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class CartItemDTO {
    private Long id;

    private itemEntity item;

    private int quantity;

    private double totalPrice;
}
