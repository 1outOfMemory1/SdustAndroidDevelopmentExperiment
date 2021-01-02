package com.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShoppingCartDao {
    void addToShoppingCart(@Param("userId") Integer userId,@Param("bookId") Integer bookId);
    void deleteFromShoppingCart(@Param("userId") Integer userId,@Param("bookId") Integer bookId);
}
