package com.bookstore.backend.dao;

import com.bookstore.backend.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {

    void addOne(OrderItem orderItem);

    void addList(List<OrderItem> orderItems);

}
