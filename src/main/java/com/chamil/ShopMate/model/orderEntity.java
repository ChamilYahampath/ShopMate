package com.chamil.ShopMate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class orderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private userEntity shopOwner;

    @ManyToOne
    private shopEntity shop;

    private double totalPrice;

    private int totalItems;

    private String description;

    private Date orderDate;

    private String status;

    @ManyToOne
    private addressEntity deliveryAddress;

    @OneToMany
    private List<orderItemEntity> items;


}
