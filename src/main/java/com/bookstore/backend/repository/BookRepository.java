package com.bookstore.backend.repository;

import com.bookstore.backend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select * from books where books.remaining > 0", nativeQuery = true)
    List<Book> findAllRemain();

}
