package com.chamil.ShopMate.service;

import com.chamil.ShopMate.dto.ItemDTO;
import com.chamil.ShopMate.dto.ResponseDTO;
import com.chamil.ShopMate.model.categoryEntity;
import com.chamil.ShopMate.model.itemEntity;
import com.chamil.ShopMate.repository.ItemRepository;
import com.chamil.ShopMate.request.CreateItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImp implements ItemService{

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public ResponseDTO createItem(CreateItemRequest req, categoryEntity category) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            itemEntity item = new itemEntity();
            item.setItemCategory(category);
            item.setDescription(req.getDescription());
            item.setName(req.getName());
            item.setPrice(req.getPrice());
            item.setQuantity(req.getQuantity());
            item.setImages(req.getImages());
            itemRepository.save(item);
            responseDTO.setCode("00");
            responseDTO.setMessage("Saved Successfully");
            responseDTO.setStatus(HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            responseDTO.setCode("10");
            responseDTO.setContent("Internal server Error");
            responseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseDTO;
    }

    @Override
    public void deleteItem(Long itemId) throws Exception {
        itemEntity item = findItemById(itemId);
        itemRepository.delete(item);
    }

    @Override
    public List<itemEntity> searchItem(String keyword) {
        return itemRepository.searchItem(keyword);
    }

    @Override
    public itemEntity findItemById(Long itemId) throws Exception {
        Optional<itemEntity> optionalItemEntity= itemRepository.findById(itemId);
        if(optionalItemEntity.isEmpty()){
            throw new Exception("Item not exist");
        }
        return optionalItemEntity.get();
    }

    @Override
    public itemEntity updateAvailabilityStatus(Long itemId) throws Exception {
        itemEntity item = findItemById(itemId);
        item.setAvailable(!item.isAvailable());
        return itemRepository.save(item);
    }

    @Override
    public List<itemEntity> getAllItems() {
        return itemRepository.findAll();
    }
}
