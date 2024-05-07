package com.chamil.ShopMate.model;

import lombok.Data;

@Data
//Not going to annotate this class as an entity because it is not going to make entity in the database
public class Contact {
    private String email;
    private String mobile;
    private String facebook;
    private String instagram;

}
