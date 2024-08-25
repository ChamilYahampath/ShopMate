package com.chamil.ShopMate.service;

import com.chamil.ShopMate.dto.ResponseDTO;
import com.chamil.ShopMate.model.categoryEntity;
import com.chamil.ShopMate.model.itemEntity;
import com.chamil.ShopMate.request.CreateItemRequest;

import java.util.List;

public interface ItemService {

    public ResponseDTO createItem(CreateItemRequest req, categoryEntity category);

    public itemEntity updateItem(Long itemId, CreateItemRequest updatedItem) throws Exception;

    void deleteItem(Long itemId) throws Exception;

    public List<itemEntity> searchItem(String keyword);

    public itemEntity findItemById(Long itemId) throws Exception;

    public itemEntity updateAvailabilityStatus(Long itemId) throws Exception;

    List<itemEntity> getAllItems();
}
