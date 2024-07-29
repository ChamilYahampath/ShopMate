package com.chamil.ShopMate.request;

import com.chamil.ShopMate.model.orderEntity;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateFeedbackRequest {
    private Long id;

    private String name;

    private String description;

    private int rating;

    private Long orderId;

    private orderEntity  orderDate;

    private Date deliverDate;

    private List<String> images;
}
