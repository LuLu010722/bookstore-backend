package com.bookstore.backend;

import com.bookstore.backend.dao.UserDao;
import com.bookstore.backend.entity.User;
import com.bookstore.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserTest {

    @Resource
    private UserService userService;

    @Resource
    private UserDao userDao;

    @Test
    public void test01() {
        User user = userDao.findOne(1L);
        user.setEmail("384668412@qq.com");
        userDao.saveOne(user);
    }
}
