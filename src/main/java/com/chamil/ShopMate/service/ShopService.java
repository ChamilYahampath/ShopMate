package com.chamil.ShopMate.service;

import com.chamil.ShopMate.model.shopEntity;
import com.chamil.ShopMate.model.userEntity;
import com.chamil.ShopMate.request.CreateShopRequest;

import java.util.List;

public interface ShopService {

    public shopEntity createShop(CreateShopRequest req, userEntity user);

    public shopEntity updateShop(Long shopId, CreateShopRequest updatedShop) throws Exception;

    public void deleteShop(Long shopId) throws Exception;

    public List<shopEntity> getAllShops();

    public List<shopEntity> searchShops(String keyword);

    public shopEntity findShopById(Long shopId) throws Exception;

    public shopEntity getShopByUserId(Long userId) throws Exception;

    public shopEntity updateShopStatus(Long shopId) throws Exception;

}
