package com.example.service.impl;

import com.example.dao.BookDao;
import com.example.entity.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public List<String> getAllBookType() {
        return bookDao.getAllBookType();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    public List<Book> getBooksByLabel(String label) {
        return bookDao.getBooksByLabel(label);
    }

    @Override
    public List<Book> getShoppingCartBooksByUsername(String username) {
        return bookDao.getShoppingCartBooksByUsername(username);
    }

    @Override
    public List<Book> getOrderBooksByUsername(String username) {
        return bookDao.getOrderBooksByUsername(username);
    }
}
