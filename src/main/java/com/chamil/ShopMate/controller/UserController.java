package com.chamil.ShopMate.controller;

import com.chamil.ShopMate.model.userEntity;
import com.chamil.ShopMate.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/api/users")

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<userEntity> findUerByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
