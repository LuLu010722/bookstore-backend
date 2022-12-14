package com.bookstore.backend.service;

import com.bookstore.backend.constant.UserServiceResponse;
import com.bookstore.backend.entity.User;
import org.springframework.data.util.Pair;

import java.util.List;

public interface UserService {
    Pair<UserServiceResponse, Long> login(String username, String password);

    void logout();

    UserServiceResponse register(String nickname, String username, String password, String email);

    User getUser();

    List<User> getAll();

    void deleteUser(Long id);

    User updateUser(User user);
}
