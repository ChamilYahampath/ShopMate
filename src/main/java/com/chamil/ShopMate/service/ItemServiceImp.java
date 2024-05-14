package com.chamil.ShopMate.service;

import com.chamil.ShopMate.model.categoryEntity;
import com.chamil.ShopMate.model.itemEntity;
import com.chamil.ShopMate.repository.ItemRepository;
import com.chamil.ShopMate.request.CreateItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImp implements ItemService{

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public itemEntity createItem(CreateItemRequest req, categoryEntity category) {
        itemEntity item = new itemEntity();
        item.setItemCategory(category);
        item.setDescription(req.getDescription());
        item.setTitle(req.getName());
        item.setPrice(req.getPrice());
        item.setQuantity(req.getQuantity());
        item.setImages(req.getImages());

        return itemRepository.save(item);
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
}
