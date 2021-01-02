package com.example.service;

import org.apache.ibatis.annotations.Param;

public interface OrderService {
    Boolean addToOrder(@Param("userId") Integer userId, @Param("bookId") Integer bookId);
    void deleteFromOrder(@Param("userId") Integer userId,@Param("bookId") Integer bookId);
}
