package com.bookstore.backend.dao;


import com.bookstore.backend.entity.Order;

import java.util.List;

public interface OrderDao {

    Order findOne(Long id);

    Order addOne(Order order);

    List<Order> findAll();

}
