package com.bookstore.backend.service;

import com.bookstore.backend.constant.OrderStatus;
import com.bookstore.backend.entity.Order;

import java.util.List;

public interface OrderService {
    OrderStatus createOrder(List<Long> bookIds, Long userId);

    List<Order> getOrders(Long userId);

    List<Order> getAllOrders();

}
