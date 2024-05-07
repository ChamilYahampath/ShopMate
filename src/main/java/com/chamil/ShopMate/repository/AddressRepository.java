package com.chamil.ShopMate.repository;

import com.chamil.ShopMate.model.addressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<addressEntity, Long> {

}
