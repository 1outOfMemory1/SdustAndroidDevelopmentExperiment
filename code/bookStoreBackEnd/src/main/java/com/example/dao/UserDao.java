package com.example.dao;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    void register(@Param("username") String username,@Param("password") String password);
    User login(@Param("username") String username, @Param("password") String password);
}
