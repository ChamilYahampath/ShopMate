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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // is using of Long fine?

    private String title;

    private String description;

    private Long price; // is using of Long fine?

    private int quantity; //check this, is datatype Long or double needed?

    @ManyToOne
    private categoryEntity itemCategory;

    @Column(length = 1000)
    @ElementCollection // This annotation is used to store a list of strings in a single column
    private List<String> images;

    private boolean available;

}
