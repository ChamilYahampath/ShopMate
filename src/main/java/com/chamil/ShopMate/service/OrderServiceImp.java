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
import java.util.Optional;
import java.util.stream.Collectors;

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
        newOrder.setDescription(order.getDescription());
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
        Long totalPrice = cartService.calculateCartTotal(cart);
        int totalItems = cartService.calculateCartTotalItems(cart);

        newOrder.setItems(orderItems);
        newOrder.setTotalPrice(totalPrice);
        newOrder.setTotalItems(totalItems);

        orderEntity savedOrder = orderRepository.save(newOrder);
        //shop.getOrders().add(savedOrder);

      //  cartService.clearCart(user.getId());

        return newOrder;
    }

    @Override
    public orderEntity updateOrder(Long orderId, String orderStatus) throws Exception {
        orderEntity order = findOrderById(orderId);
        if(orderStatus.equals("OUT_FOR_DELIVERY")
                || orderStatus.equals("DELIVERED")
                || orderStatus.equals("COMPLETED")
                || orderStatus.equals("CANCELLED")
                || orderStatus.equals("PENDING")){
            order.setStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("Please select a valid order status.");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        orderEntity order = findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<orderEntity> getUsersOrder(Long userId) throws Exception {
        return orderRepository.findByShopOwnerId(userId);
    }

    @Override
    public List<orderEntity> getShopsOrder(Long shopId, String orderStatus) throws Exception {
        List<orderEntity> orders = orderRepository.findByShopId(shopId);
        if(orderStatus != null){
            orders = orders.stream()
                    .filter(order -> order.getStatus().equals(orderStatus))
                    .collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public List<orderEntity> getAllOrders() throws Exception {
        return orderRepository.findAll();
    }

    @Override
    public orderEntity findOrderById(Long orderId) throws Exception {
        Optional<orderEntity> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new Exception("Order not found.");
        }
        return optionalOrder.get();
    }

}
