package com.bookstore.backend.service;


import com.bookstore.backend.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBook(Long id);

    Book saveBook(Book book);

    void deleteById(Long id);
}
