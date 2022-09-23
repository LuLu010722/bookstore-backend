package com.bookstore.backend.repository;

import com.bookstore.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Query(value = "insert into orders (id, user_id, `status`, time_stamp) values (:#{#order.id}, :#{order.user.id}, " +
            ":#{#order.status}, :#{#order.timeStamp})", nativeQuery = true)
    Integer mySaveOne(Order order);
}
