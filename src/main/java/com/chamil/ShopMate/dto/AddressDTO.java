package com.chamil.ShopMate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDTO {

    private Long id;

    private String Street;

    private String city;

    private String province;

    private String postalCode;
}
