package com.bookstore.backend.dao.daoimpl;

import com.bookstore.backend.dao.OrderDao;
import com.bookstore.backend.entity.Order;
import com.bookstore.backend.repository.OrderRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Resource
    private OrderRepository orderRepository;

    @Override
    public Order findOne(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer addOne(Order order) {
        Integer result = orderRepository.mySaveOne(order);

//        int error = 10 / 0;
        return result;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
