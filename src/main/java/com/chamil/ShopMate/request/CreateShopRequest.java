package com.chamil.ShopMate.request;

import com.chamil.ShopMate.model.Contact;
import com.chamil.ShopMate.model.addressEntity;
import lombok.Data;

import java.util.List;

@Data
public class CreateShopRequest {
    private Long id;
    private String name;
    private String description;
    private addressEntity address;
    private Contact contact;
    private String openingHours;
    private List<String> images;
    private boolean status;
}