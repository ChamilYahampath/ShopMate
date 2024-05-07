package com.chamil.ShopMate.service;

import com.chamil.ShopMate.config.JwtProvider;
import com.chamil.ShopMate.model.userEntity;
import com.chamil.ShopMate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public userEntity findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromToken(jwt);
        userEntity user = findUserByEmail(email);
        return user;
    }

    @Override
    public userEntity findUserByEmail(String email) throws Exception {
        userEntity user = userRepository.findByEmail(email);

        if(user == null){
            throw new Exception("User not found");
        }
        return user;
    }
}
