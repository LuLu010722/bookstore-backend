package com.bookstore.backend;

import com.bookstore.backend.dao.BookDao;
import com.bookstore.backend.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrderServiceTest {

    @Resource
    private OrderService orderService;

    @Resource
    private BookDao bookDao;

    @Test
    public void test01() {
        List<Long> bookIds = new ArrayList<>();
        bookIds.add(1L);
        bookIds.add(2L);
        bookIds.add(3L);
        orderService.createOrder(bookIds, 1L);
    }
}
