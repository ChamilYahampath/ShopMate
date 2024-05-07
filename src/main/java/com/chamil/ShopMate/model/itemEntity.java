package com.chamil.ShopMate.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class itemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private double price;

    @ManyToOne
    private categoryEntity itemCategory;

    @Column(length = 1000)
    @ElementCollection // This annotation is used to store a list of strings in a single column
    private List<String> images;

    private boolean available;

}
