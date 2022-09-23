package com.bookstore.backend.dao;

import com.bookstore.backend.entity.User;

import java.util.List;

public interface UserDao {
    User findOne(Long id);

    User saveOne(User user);

    List<User> findAll();

    void deleteOne(Long id);

    User updateUser(User user);

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

}
