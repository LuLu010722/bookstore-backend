package com.bookstore.backend.repository;

import com.bookstore.backend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Modifying
    @Query(value = "insert into order_items (id, order_id, book_id, amount, price) values (:#{#orderItem.id}, " +
            ":#{#orderItem.order.id}, :#{#orderItem.book.id}, :#{#orderItem.amount}, :#{#orderItem.price})",
            nativeQuery = true)
    void mySaveOne(OrderItem orderItem);

}
