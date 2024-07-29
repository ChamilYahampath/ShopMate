package com.chamil.ShopMate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comments;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO {
    private HttpStatus status;
    private String code;
    private String message;
    private Object content;
}
