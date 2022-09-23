package com.bookstore.backend.dao.daoimpl;

import com.bookstore.backend.dao.BookDao;
import com.bookstore.backend.entity.Book;
import com.bookstore.backend.repository.BookRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Resource
    private BookRepository bookRepository;

    @Override
    public Book findOne(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book addOne(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAllRemain();
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
