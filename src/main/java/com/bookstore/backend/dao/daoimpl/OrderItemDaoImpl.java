package com.bookstore.backend.dao.daoimpl;

import com.bookstore.backend.dao.OrderItemDao;
import com.bookstore.backend.entity.OrderItem;
import com.bookstore.backend.repository.OrderItemRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {

    @Resource
    private OrderItemRepository orderItemRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addOne(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addList(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            orderItemRepository.mySaveOne(orderItem);
        }
//        int error = 10 / 0;
    }
}
