package com.bookstore.backend.service.serviceimpl;

import com.bookstore.backend.constant.UserServiceResponse;
import com.bookstore.backend.constant.UserType;
import com.bookstore.backend.dao.UserDao;
import com.bookstore.backend.entity.User;
import com.bookstore.backend.service.TimerService;
import com.bookstore.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.bookstore.backend.util.SessionUtil.*;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    public UserServiceResponse login(String username, String password) {
        System.out.println(this);
        log.info("logging in");

        if (username == null) {
            log.warn("login failed: username can't be blank!");
            return UserServiceResponse.LOGIN_USERNAME_INVALID;
        }

        if (password == null) {
            log.warn("login failed: password can't be blank!");
            return UserServiceResponse.LOGIN_PASSWORD_INVALID;
        }

        User user = userDao.findByUsernameAndPassword(username, password);
        if (user == null) {
            log.warn("login failed: user not found!");
            return UserServiceResponse.LOGIN_NOT_FOUND;
        }

        if (user.getUserType() == UserType.FORBIDDEN) {
            log.warn("login failed: user is forbidden");
            return UserServiceResponse.LOGIN_FORBIDDEN;
        }

        createSession(user.getId(), user.getUserType());

        log.info("login succeeded");

        if (user.getUserType() == UserType.ADMIN) {
            return UserServiceResponse.LOGIN_ADMIN;
        }

        return UserServiceResponse.LOGIN_CUSTOMER;
    }

    @Override
    public void logout() {
        removeSession();
    }

    public UserServiceResponse register(String nickname, String username, String password, String email) {

        log.info("registering");

        if (StringUtils.isAnyBlank(nickname, username, password, email)) {
            return UserServiceResponse.REGISTER_USERNAME_INVALID;
        }

        User user1 = userDao.findByUsername(username);

        if (user1 != null) {
            return UserServiceResponse.REGISTER_DUPLICATE;
        }

        User user = new User();
        user.setNickname(nickname);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setUserType(UserType.CUSTOMER);
        userDao.saveOne(user);

        createSession(user.getId(), UserType.CUSTOMER);
        log.info("register succeeded");
        return UserServiceResponse.REGISTER_ALL_OK;
    }

    @Override
    public User getUser() {
        Long userId = getUserId();
        return userDao.findOne(userId);
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteOne(id);
    }

    @Override
    public User updateUser(User user) {
        return userDao.saveOne(user);
    }

}
