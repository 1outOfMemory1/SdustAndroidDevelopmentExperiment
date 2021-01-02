package com.example.entity;

import java.util.List;
/*
create table user(
     userId int primary key,
     username varchar(50) unique key ,
     password varchar(50)
)
* */


public class User {
    private Integer userId;
    private String username;
    private String password;
//    private List<Book> shoppingCartList;
//    private List<Book> orderList;

    public User(Integer userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
