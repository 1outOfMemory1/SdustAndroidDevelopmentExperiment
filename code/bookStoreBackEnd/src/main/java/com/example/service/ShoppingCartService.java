package com.example.service;

import org.apache.ibatis.annotations.Param;

public interface ShoppingCartService {
    Boolean addToShoppingCart(@Param("userId") Integer userId, @Param("bookId") Integer bookId);
    void deleteFromShoppingCart(@Param("userId") Integer userId,@Param("bookId") Integer bookId);
}
