package com.chamil.ShopMate.request;

import lombok.Data;

@Data
public class AddCartItemRequest {

    private Long itemId;

    private int quantity;


}
