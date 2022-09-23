package com.bookstore.backend.controller;


import com.bookstore.backend.entity.Book;
import com.bookstore.backend.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    @GetMapping("/all")
    public List<Book> getAllBook() {
        return bookService.getAllBooks();
    }

    @GetMapping("/select")
    public Book getBook(@RequestParam("id") Long id) {
        return bookService.getBook(id);
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping("/delete")
    public void deleteBook(@RequestParam("book-id") Long id) {
        bookService.deleteById(id);
    }

}
