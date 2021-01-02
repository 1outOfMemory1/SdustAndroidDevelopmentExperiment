package com.example.controller;

import com.example.entity.Book;
import com.example.entity.User;
import com.example.service.BookService;
import com.example.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("bookApi")
@CrossOrigin

public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("getAllBooks")
    public ResultMessage getAllBooks(){
        return new ResultMessage(ResultMessage.code.SUCCESS,ResultMessage.message.SUCCESS,bookService.getAllBooks());
    }

    @RequestMapping("getBooksByLabel")
    public ResultMessage getBooksByLabel(String label){
        List<Book> bookList = bookService.getBooksByLabel(label);
        if(bookList.size() != 0)
            return new ResultMessage(ResultMessage.code.SUCCESS,ResultMessage.message.SUCCESS,bookList);
        else
            return new ResultMessage(ResultMessage.code.FAILURE,ResultMessage.message.FAILURE,null);
    }

    @RequestMapping("getShoppingCartBooksByUsername")
    public ResultMessage getShoppingCartBooksByUsername(String username){
        return new ResultMessage(200,"查询购物车列表成功", bookService.getShoppingCartBooksByUsername(username));
    }

    @RequestMapping("getOrderBooksByUsername")
    public ResultMessage getOrderBooksByUsername(String username){
        return new ResultMessage(200,"查询订单信息成功",bookService.getOrderBooksByUsername(username));
    }

}
