package com.example.service;

import com.example.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    List<Book> getBooksByLabel(String label);
    List<Book> getShoppingCartBooksByUsername(String username);
    List<Book> getOrderBooksByUsername(String username);
}
