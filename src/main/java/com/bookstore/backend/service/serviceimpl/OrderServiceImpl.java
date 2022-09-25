package com.bookstore.backend.service.serviceimpl;

import com.bookstore.backend.constant.OrderStatus;
import com.bookstore.backend.dao.BookDao;
import com.bookstore.backend.dao.OrderDao;
import com.bookstore.backend.dao.OrderItemDao;
import com.bookstore.backend.dao.UserDao;
import com.bookstore.backend.entity.Book;
import com.bookstore.backend.entity.Order;
import com.bookstore.backend.entity.OrderItem;
import com.bookstore.backend.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private OrderItemDao orderItemDao;

    @Resource
    private BookDao bookDao;

    @Resource
    private UserDao userDao;


//    @Override
//    public OrderStatus createOrder(List<Long> bookIds, Long userId) {
//
//        log.info("creating order");
//
//        Order order = new Order();
//        List<OrderItem> orderItems = new ArrayList<>();
//
//        for (Long id : bookIds) {
//            Book book = bookDao.findOne(id);
//            if (book.getRemaining() == 0) {
//                log.error(book.getTitle() + "已经售罄");
//                return null;
//            }
//
//            book.setRemaining(book.getRemaining() - 1);
//            bookDao.addOne(book);
//
//            OrderItem orderItem = new OrderItem();
//
////            orderItem.setOrder(order);
//            orderItem.setAmount(1);
//            orderItem.setBook(book);
//            orderItem.setPrice(book.getPrice());
//
//            orderItems.add(orderItem);
//        }
//
//        order.setUser(userDao.findOne(userId));
//        order.setOrderItems(orderItems);
//        orderDao.addOne(order);
//
//        return OrderStatus.ORDER_ALL_OK;
//    }

    @Override
    @Transactional
    public OrderStatus createOrder(List<Long> bookIds, Long userId) {

        // first part
        Order order = new Order();
        order.setUser(userDao.findOne(userId));
        // this method includes transaction
        orderDao.addOne(order);

//      create error before part 2
        int error = 10 / 0;

        // second part
        List<OrderItem> orderItems = new ArrayList<>();
        for (Long bookId : bookIds) {
            Book book = bookDao.findOne(bookId);
            if (book.getRemaining() == 0) {
                log.error(book.getTitle() + "已经售罄！");
                return OrderStatus.ERROR;
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setAmount(1);
            orderItem.setBook(book);
            orderItem.setPrice(book.getPrice());

            orderItems.add(orderItem);
        }

//      this method includes transaction
        orderItemDao.addList(orderItems);

//      create error after part 2
//        int error = 10 / 0;

        return OrderStatus.ORDER_ALL_OK;
    }

    @Override
    public List<Order> getOrders(Long userId) {

        List<Order> orders = userDao.findOne(userId).getOrders();
        return orders;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }
}
