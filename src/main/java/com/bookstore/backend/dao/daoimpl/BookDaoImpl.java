package com.bookstore.backend.dao.daoimpl;

import com.alibaba.fastjson.JSONArray;
import com.bookstore.backend.dao.BookDao;
import com.bookstore.backend.entity.Book;
import com.bookstore.backend.repository.BookRepository;
import com.bookstore.backend.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Slf4j
public class BookDaoImpl implements BookDao {

    @Resource
    private BookRepository bookRepository;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public Book findOne(Long id) {
        Object o = redisUtil.get("book" + id);
        if (o != null) {
            log.info("book " + id + " in redis");
            return JSONArray.parseObject(o.toString(), Book.class);
        } else {
            log.info("book " + id + " not in redis");
            Book book = bookRepository.findById(id).orElse(null);
            redisUtil.set("book" + id, JSONArray.toJSON(book));
            return book;
        }
    }

    @Override
    public Book addOne(Book book) {
        Book savedBook = bookRepository.save(book);
        redisUtil.set("book" + savedBook.getId(), JSONArray.toJSON(savedBook));
        return savedBook;
    }

    @Override
    public List<Book> getAll() {
        List<Book> allRemain = bookRepository.findAllRemain();
        for (Book book : allRemain) {
            redisUtil.set("book" + book.getId(), JSONArray.toJSON(book));
        }
        return allRemain;
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
        Object o = redisUtil.get("book" + id);
        if (o != null) {
            redisUtil.del("book" + id);
        }
    }
}
