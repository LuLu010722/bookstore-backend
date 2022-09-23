package com.bookstore.backend.dao;


import com.bookstore.backend.entity.Book;

import java.util.List;

public interface BookDao {
    Book findOne(Long id);

    Book addOne(Book book);

    List<Book> getAll();

    void deleteById(Long id);
}
