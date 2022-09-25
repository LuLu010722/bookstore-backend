package com.bookstore.backend.controller;


import com.bookstore.backend.constant.OrderStatus;
import com.bookstore.backend.entity.Book;
import com.bookstore.backend.entity.Order;
import com.bookstore.backend.service.OrderService;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.bookstore.backend.util.SessionUtil.getUserId;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    public OrderStatus createOrder(@RequestBody List<Long> bookIds) {
//        便于测试
//        Long userId = getUserId();
        Long userId = 6L;
        return orderService.createOrder(bookIds, userId);
    }

    @GetMapping("/get")
    public List<Order> getOrders() {
        Long userId = getUserId();
        return orderService.getOrders(userId);
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

}
