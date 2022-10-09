package com.bookstore.backend.controller;


import com.bookstore.backend.constant.OrderStatus;
import com.bookstore.backend.entity.Order;
import com.bookstore.backend.service.OrderService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.bookstore.backend.util.SessionUtil.getUserId;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/create")
    public OrderStatus createOrder(@RequestBody List<Long> bookIds) throws Exception {
        Long userId = getUserId();
        String data = bookIds.toString() + userId;

        // data format: [{id1}, {id2}, {id3}]{userid}

        kafkaTemplate.send("topic1", "key", data);
        return OrderStatus.ORDER_ALL_OK;
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
