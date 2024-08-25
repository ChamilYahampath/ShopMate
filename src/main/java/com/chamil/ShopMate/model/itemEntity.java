package com.chamil.ShopMate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class itemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long price;

    private int quantity;

    @ManyToOne
    private categoryEntity itemCategory;

    @Column(length = 1000)
    @ElementCollection // This annotation is used to store a list of strings in a single column
    private List<String> images;

}
