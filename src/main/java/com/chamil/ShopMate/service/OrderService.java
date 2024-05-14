package com.chamil.ShopMate.service;

import com.chamil.ShopMate.model.orderEntity;
import com.chamil.ShopMate.model.userEntity;
import com.chamil.ShopMate.request.OrderRequest;

import java.util.List;

public interface OrderService {

    public orderEntity createOrder(OrderRequest order, userEntity user) throws Exception;

    public orderEntity updateOrder(Long orderId, String orderStatus) throws Exception;

    public void cancelOrder(Long orderId) throws Exception;

    public List<orderEntity> getUsersOrder(Long userId) throws Exception;

    public List<orderEntity> getShopsOrder(Long shopId, String orderStatus) throws Exception;

    public orderEntity findOrderById(Long orderId) throws Exception;
}
