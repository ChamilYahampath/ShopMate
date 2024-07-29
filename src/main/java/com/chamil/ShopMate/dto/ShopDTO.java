package com.chamil.ShopMate.dto;

import com.chamil.ShopMate.model.Contact;
import com.chamil.ShopMate.model.addressEntity;
import com.chamil.ShopMate.model.orderEntity;
import com.chamil.ShopMate.model.userEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShopDTO {

    private Long id;

    private userEntity owner;

    private String name;

    private String description;

    private AddressDTO address;

    private Contact contact;

    private List<String> images;

    private String openingHours;

    private LocalDateTime registeredDate;

    private boolean status;

    private List<orderEntity> orders;
}
