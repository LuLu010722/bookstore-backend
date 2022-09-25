package com.bookstore.backend.dao.daoimpl;

import com.bookstore.backend.dao.OrderItemDao;
import com.bookstore.backend.entity.OrderItem;
import com.bookstore.backend.repository.OrderItemRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {

    @Resource
    private OrderItemRepository orderItemRepository;

    @Override
    public void addOne(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
    public void addList(List<OrderItem> orderItems) {
        orderItemRepository.saveAll(orderItems);
//        int error = 10 / 0;
    }
}
