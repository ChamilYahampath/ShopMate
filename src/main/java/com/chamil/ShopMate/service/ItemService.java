package com.chamil.ShopMate.service;

import com.chamil.ShopMate.model.categoryEntity;
import com.chamil.ShopMate.model.itemEntity;
import com.chamil.ShopMate.request.CreateItemRequest;

import java.util.List;

public interface ItemService {

    public itemEntity createItem(CreateItemRequest req, categoryEntity category);

    void deleteItem(Long itemId) throws Exception;

    public List<itemEntity> searchItem(String keyword);

    public itemEntity findItemById(Long itemId) throws Exception;

    public itemEntity updateAvailabilityStatus(Long itemId) throws Exception;
}
