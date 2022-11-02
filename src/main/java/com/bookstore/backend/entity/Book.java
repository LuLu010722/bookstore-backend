package com.bookstore.backend.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity(name = "books")
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date publishTime;

    private String language;

    private String description;

    private BigDecimal price;

    private String imgUrl;

    private Integer remaining;

    private String isbn;

}