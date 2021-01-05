package com.example.dao;


import com.example.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookDao {
    List<String> getAllBookType();
    List<Book> getAllBooks();
    List<Book> getBooksByLabel(@Param("label") String label);

    List<Book> getShoppingCartBooksByUsername(@Param("username") String username);

    List<Book> getOrderBooksByUsername(@Param("username") String username);
}
