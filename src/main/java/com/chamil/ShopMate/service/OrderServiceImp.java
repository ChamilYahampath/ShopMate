package com.chamil.ShopMate.service;

import com.chamil.ShopMate.model.*;
import com.chamil.ShopMate.repository.AddressRepository;
import com.chamil.ShopMate.repository.OrderItemRepository;
import com.chamil.ShopMate.repository.OrderRepository;
import com.chamil.ShopMate.repository.UserRepository;
import com.chamil.ShopMate.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShopService shopService;

    @Autowired
    private CartService cartService;

    @Override
    public orderEntity createOrder(OrderRequest order, userEntity user) throws Exception {
        addressEntity shippingAddress = order.getDeliveryAddress();
        addressEntity savedAddress = addressRepository.save(shippingAddress);

        if(!user.getAddresses().contains(savedAddress)){
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }

        shopEntity shop = shopService.findShopById(order.getShopId());

        orderEntity newOrder = new orderEntity();
        newOrder.setShopOwner(user);
        newOrder.setShop(shop);
        newOrder.setDeliveryAddress(savedAddress);
        newOrder.setStatus("PENDING");
        newOrder.setOrderDate(new Date());

        cartEntity cart = cartService.findCartByUserId(user.getId());

        List<orderItemEntity> orderItems = new ArrayList<>();

        for(cartItemEntity cartItem : cart.getItems()){
            orderItemEntity orderItem = new orderItemEntity();
            orderItem.setItem(cartItem.getItem());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            orderItemEntity savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }

        newOrder.setItems(orderItems);
        newOrder.setTotalPrice(cart.getTotalPrice()); //if not working check later, different method used 7.36


        return null;
    }

    @Override
    public orderEntity updateOrder(Long orderId, String orderStatus) throws Exception {
        return null;
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {

    }

    @Override
    public List<orderEntity> getUsersOrder(Long userId) throws Exception {
        return null;
    }

    @Override
    public List<orderEntity> getShopsOrder(Long shopId, String orderStatus) throws Exception {
        return null;
    }

}
