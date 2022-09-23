package com.bookstore.backend.entity;

import com.bookstore.backend.constant.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "users")
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String username;

    private String password;

    @Column(name = "type")
    private UserType userType;

    private String email;

    private String phone;

    private String address;

    private String avatarImgUrl;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = "user")
    private List<Order> orders;

}