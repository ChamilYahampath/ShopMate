package com.chamil.ShopMate.service;

import com.chamil.ShopMate.model.cartEntity;
import com.chamil.ShopMate.model.cartItemEntity;
import com.chamil.ShopMate.model.itemEntity;
import com.chamil.ShopMate.model.userEntity;
import com.chamil.ShopMate.repository.CartItemRepository;
import com.chamil.ShopMate.repository.CartRepository;
import com.chamil.ShopMate.repository.ItemRepository;
import com.chamil.ShopMate.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImp implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Override
    public cartItemEntity addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);

        itemEntity item = itemService.findItemById(req.getItemId());

        cartEntity cart = cartRepository.findCartByShopOwnerId(user.getId());

        for(cartItemEntity cartItem : cart.getItems()){
            if(cartItem.getItem().equals(item)){
                int newQuantity=cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }

        cartItemEntity newCartItem = new cartItemEntity();
        newCartItem.setCart(cart);
        newCartItem.setItem(item);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setTotalPrice(item.getPrice() * req.getQuantity());

        cartItemEntity savedCartItem = cartItemRepository.save(newCartItem);

        cart.getItems().add(savedCartItem);

        return savedCartItem;
    }

    @Override
    public cartItemEntity updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<cartItemEntity> cartItemOptional = cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new Exception("Cart Item not found");
        }
        cartItemEntity cartItem = cartItemOptional.get();
        cartItem.setQuantity(quantity);

        cartItem.setTotalPrice(cartItem.getItem().getPrice() * quantity);

        return cartItemRepository.save(cartItem);
    }

    @Override
    public cartEntity removeCartItem(Long cartItemId, String jwt) throws Exception {
        userEntity user = userService.findUserByJwtToken(jwt);

        cartEntity cart = cartRepository.findCartByShopOwnerId(user.getId());

        Optional<cartItemEntity> cartItemOptional = cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new Exception("Cart Item not found");
        }

        cartItemEntity cartItem = cartItemOptional.get();


        cart.getItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        cart.setTotalPrice(calculateCartTotal(cart));
        cart.setTotalItems(calculateCartTotalItems(cart));
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotal(cartEntity cart) throws Exception {
        Long total = 0L;

        for(cartItemEntity cartItem : cart.getItems()){
            total += cartItem.getItem().getPrice()*cartItem.getQuantity();
        }
        return total;
    }
    @Override
    public int calculateCartTotalItems(cartEntity cart) throws Exception {
        int total = 0;

        for(cartItemEntity cartItem : cart.getItems()){
            total += 1;
        }
        return total;
    }

    @Override
    public cartEntity findCartById(Long id) throws Exception {
        Optional<cartEntity> cartOptional = cartRepository.findById(id);
        if(cartOptional.isEmpty()){
            throw new Exception("Cart not found with id "+id+":");
        }
        return cartOptional.get();
    }

    @Override
    public cartEntity findCartByUserId(Long userId) throws Exception {
        cartEntity cart = cartRepository.findCartByShopOwnerId(userId);
        cart.setTotalPrice(calculateCartTotal(cart));
        cart.setTotalItems(calculateCartTotalItems(cart));
        return cart;
    }

    @Override
    public cartEntity clearCart(Long userId) throws Exception {
        cartEntity cart = cartRepository.findCartByShopOwnerId(userId);
        cart.getItems().clear();

        return cartRepository.save(cart);
    }

}
