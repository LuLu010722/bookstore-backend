package com.bookstore.backend.controller;

import com.bookstore.backend.constant.UserServiceResponse;
import com.bookstore.backend.entity.User;
import com.bookstore.backend.service.TimerService;
import com.bookstore.backend.service.UserService;
import lombok.Data;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private WebApplicationContext webApplicationContext;

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Pair<UserServiceResponse, Long> userLogin(@RequestBody UserLoginRequest userLoginRequest) {

        TimerService timerService = webApplicationContext.getBean(TimerService.class);
        timerService.start();

        String username = userLoginRequest.getUsername();
        String password = userLoginRequest.getPassword();

        return userService.login(username, password);

    }

    /**
     * User logout.
     *
     * @return The total online time in millis.
     */
    @GetMapping("/logout")
    public Long userLogout() {

        TimerService timerService = webApplicationContext.getBean(TimerService.class);
        Long duration = timerService.stop();

        userService.logout();
        return duration;
    }

    @PostMapping("/register")
    public UserServiceResponse userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        String nickname = userRegisterRequest.getNickname();
        String username = userRegisterRequest.getUsername();
        String password = userRegisterRequest.getPassword();
        String email = userRegisterRequest.getEmail();
        return userService.register(nickname, username, password, email);
    }

    @GetMapping("/get")
    public User getUser() {
        return userService.getUser();
    }


    @PostMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }


    @GetMapping("/delete")
    public Boolean deleteUser(@RequestParam("user-id") Long userId) {
        userService.deleteUser(userId);
        return true;
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAll();
    }


    @Data
    private static class UserLoginRequest {
        private String username;
        private String password;
    }

    @Data
    private static class UserRegisterRequest {
        private String nickname;
        private String username;
        private String password;
        private String email;
    }
}
