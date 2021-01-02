package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("userApi")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("register")
    public ResultMessage register(String username,String password){
        userService.register(username, password);
        return new ResultMessage(200,"注册成功",null);
    }

    @RequestMapping("login")
    public ResultMessage login(String username,String password){
        User user = userService.login(username, password);
        if (user == null){
            return new ResultMessage(300,"登录失败，用户名或者密码错误",null);
        }
        else {
            return new ResultMessage(200,"登录成功",user);
        }
    }
}
