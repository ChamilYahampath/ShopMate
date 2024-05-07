package com.chamil.ShopMate.response;

import com.chamil.ShopMate.model.userRole;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private userRole role;
}
