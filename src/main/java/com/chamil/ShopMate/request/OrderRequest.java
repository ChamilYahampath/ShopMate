package com.chamil.ShopMate.request;

import com.chamil.ShopMate.model.addressEntity;
import lombok.Data;

@Data
public class OrderRequest {

    private Long shopId;
    private addressEntity deliveryAddress;

    //private String orderStatus;
    //private String orderDate;

}
