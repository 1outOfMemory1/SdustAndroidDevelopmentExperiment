package com.example.service;

import com.example.entity.User;
import com.example.util.ResultMessage;

import java.util.List;

public interface UserService {
    void register(String username,String password);
    User login(String username, String password);
}
