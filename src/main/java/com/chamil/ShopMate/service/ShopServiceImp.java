package com.chamil.ShopMate.service;

import com.chamil.ShopMate.dto.AddressDTO;
import com.chamil.ShopMate.dto.ResponseDTO;
import com.chamil.ShopMate.dto.ShopDTO;
import com.chamil.ShopMate.model.addressEntity;
import com.chamil.ShopMate.model.itemEntity;
import com.chamil.ShopMate.model.shopEntity;
import com.chamil.ShopMate.model.userEntity;
import com.chamil.ShopMate.repository.AddressRepository;
import com.chamil.ShopMate.repository.ShopRepository;
import com.chamil.ShopMate.request.CreateShopRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImp implements ShopService{

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;

    @Override
    public shopEntity createShop(CreateShopRequest req, userEntity user) {

        addressEntity address = addressRepository.save(req.getAddress());

        shopEntity shop = new shopEntity();
        shop.setAddress(address);
        shop.setContact(req.getContact());
        shop.setDescription(req.getDescription());
        shop.setName(req.getName());
        shop.setOpeningHours(req.getOpeningHours());
        shop.setOwner(user);
        shop.setStatus(true);
        shop.setImages(req.getImages());
        shop.setRegisteredDate(LocalDateTime.now());

        return shopRepository.save(shop);
    }

    @Override
    public shopEntity updateShop(Long shopId, CreateShopRequest updatedShop) throws Exception {
        shopEntity shop = findShopById(shopId);

        if(shop.getName()!=null){
            shop.setName(updatedShop.getName());
        }

        if (shop.getDescription()!=null){
            shop.setDescription(updatedShop.getDescription());
        }

        if (shop.getAddress()!=null){
            shop.setAddress(updatedShop.getAddress());
        }

        if (shop.getContact()!=null){
            shop.setContact(updatedShop.getContact());
        }

        if (shop.getImages()!=null){
            shop.setImages(updatedShop.getImages());
        }

        if (shop.getOpeningHours()!=null){
            shop.setOpeningHours(updatedShop.getOpeningHours());
        }

        return shopRepository.save(shop);
    }


    @Override
    public void deleteShop(Long shopId) throws Exception {
        shopEntity shop = findShopById(shopId);
        shopRepository.delete(shop);
    }

    @Override
    public List<shopEntity> getAllShops() {
        return shopRepository.findAll();
    }

    @Override
    public List<shopEntity> searchShops(String keyword) {
        return shopRepository.findBySearchQuery(keyword);
    }

    @Override
    public shopEntity findShopById(Long shopId) throws Exception {
        Optional<shopEntity> opt = shopRepository.findById(shopId);

        if(opt.isEmpty()){
            throw new Exception("Shop not found with id: "+shopId);
        }
        return opt.get();
    }

    @Override
    public ResponseDTO getShopByUserId(Long userId) {
        ResponseDTO responseDTO = new ResponseDTO();

        try{
            shopEntity shop = shopRepository.findByOwnerId(userId);
            ShopDTO shopDTO = new ShopDTO();
            shopDTO.setId(shop.getId());
            shopDTO.setName(shop.getName());
            shopDTO.setContact(shop.getContact());
            shopDTO.setDescription(shop.getDescription());
            shopDTO.setStatus(shop.isStatus());
            shopDTO.setOwner(shop.getOwner());
            shopDTO.setOpeningHours(shop.getOpeningHours());
            AddressDTO addressDTO = new AddressDTO();
            addressEntity addressEntity = shop.getAddress();
            addressDTO.setId(addressEntity.getId());
            addressDTO.setCity(addressEntity.getCity());
            addressDTO.setStreet(addressEntity.getStreet());
            addressDTO.setProvince(addressEntity.getProvince());
            addressDTO.setPostalCode(addressEntity.getPostalCode());
            shopDTO.setAddress(addressDTO);
            responseDTO.setContent(shopDTO);
            responseDTO.setCode("00");
            responseDTO.setMessage("Saved Successfully");
            responseDTO.setStatus(HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            responseDTO.setCode("10");
            responseDTO.setContent("Internal server Error");
            responseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseDTO;
    }

    @Override
    public shopEntity updateShopStatus(Long shopId) throws Exception {
        shopEntity shop = findShopById(shopId);
        shop.setStatus(!shop.isStatus());
        return shopRepository.save(shop);
    }
}
