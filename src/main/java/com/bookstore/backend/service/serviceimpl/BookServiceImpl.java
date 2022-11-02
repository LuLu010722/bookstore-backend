package com.bookstore.backend.service.serviceimpl;

import com.bookstore.backend.dao.BookDao;
import com.bookstore.backend.entity.Book;
import com.bookstore.backend.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Resource
    private BookDao bookDao;

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @Override
    public Book getBook(Long id) {
        return bookDao.findOne(id);
    }

    @Override
    public Book saveBook(Book book) {
        return bookDao.addOne(book);
    }

    @Override
    public void deleteById(Long id) {
        bookDao.deleteById(id);
    }
}
