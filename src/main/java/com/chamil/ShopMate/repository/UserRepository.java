package com.chamil.ShopMate.repository;

import com.chamil.ShopMate.model.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<userEntity, String> {

    public userEntity findByEmail(String email);

}
