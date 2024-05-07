package com.chamil.ShopMate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String orderDate;

    private String deliveryDate;

    private String status;

    @ManyToOne
    private addressEntity deliveryAddress;

    private String deliveryDriver;

    private String deliveryDriverContact;

    private String deliveryDriverVehicleNumber;

    @OneToMany
    private List<orderItemEntity> items;

    //private String paymentMethod;

}
