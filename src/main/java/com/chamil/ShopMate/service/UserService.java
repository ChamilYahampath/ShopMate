package com.chamil.ShopMate.service;

import com.chamil.ShopMate.model.userEntity;

public interface UserService {

    public userEntity findUserByJwtToken(String jwt) throws Exception;

    public userEntity findUserByEmail(String email) throws Exception;

}
