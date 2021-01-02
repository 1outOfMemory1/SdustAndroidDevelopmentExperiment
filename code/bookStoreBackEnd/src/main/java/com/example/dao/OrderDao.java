package com.example.dao;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    void addToOrder(@Param("userId") Integer userId,@Param("bookId") Integer bookId);
    void deleteFromOrder(@Param("userId") Integer userId,@Param("bookId") Integer bookId);
}
