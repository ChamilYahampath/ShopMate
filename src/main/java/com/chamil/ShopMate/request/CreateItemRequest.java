package com.chamil.ShopMate.request;

import com.chamil.ShopMate.model.categoryEntity;
import lombok.Data;

import java.util.List;

@Data
public class CreateItemRequest {

    private String name;
    private String description;
    private Long price;
    private int quantity;
    private List<String> images;
    private categoryEntity category;

}
