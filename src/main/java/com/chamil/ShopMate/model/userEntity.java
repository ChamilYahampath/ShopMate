package com.chamil.ShopMate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class userEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private userRole role;

    private String address;

    private int contact;

    private String nic;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopOwner") //didn't create separate table for mapping
    private List<orderEntity> orders = new ArrayList<>();

    @OneToMany
    private List<itemEntity> items = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //if remove the user, addresses will not delete
    private List<addressEntity> addresses = new ArrayList<>();

}
