package com.bookstore.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "order_items")
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;

    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = "orderItems")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
