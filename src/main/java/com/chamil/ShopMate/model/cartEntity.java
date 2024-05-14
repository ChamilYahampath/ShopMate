package com.chamil.ShopMate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class cartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private userEntity shopOwner;

    private double totalPrice;

    private int totalItems;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<cartItemEntity> items = new ArrayList<>();
}
