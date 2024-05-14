package com.chamil.ShopMate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class shopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne //ask about this relationship
    private userEntity owner;

    private String name;

    private String description;

    @OneToOne
    private addressEntity address;

    //Not added the contact entity
    @Embedded
    private Contact contact;

    @ElementCollection
    @Column(length = 1000)
    private List<String> images;

    private String openingHours;

    private LocalDateTime registeredDate;

    private boolean status;

    @OneToMany
    private List<orderEntity> orders;
}
