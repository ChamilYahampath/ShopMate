package com.chamil.ShopMate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class feedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int rating;

    private LocalDateTime feedbackDate;

    private Date deliverDate;


    private Date orderDate;


    private Long orderId;

    @Column(length = 1000)
    @ElementCollection // This annotation is used to store a list of strings in a single column
    private List<String> images;
}
