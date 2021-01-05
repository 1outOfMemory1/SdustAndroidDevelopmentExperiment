package com.example.controller;

import com.example.entity.UserShoppingCartOrOrder;
import com.example.service.OrderService;
import com.example.service.ShoppingCartService;
import com.example.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("restApi")
public class ShoppingCardAndOrderController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderService orderService;


    @RequestMapping("shoppingCart/add")
    public ResultMessage addToShoppingCart(Integer userId,Integer bookId){
        if(shoppingCartService.addToShoppingCart(userId, bookId))
            return new ResultMessage(200,"插入购物车成功",true);
        else return new ResultMessage(300,"该书籍购物车已经存在",false);
    }

    @RequestMapping("shoppingCart/delete")
    public ResultMessage deleteFromShoppingCart(@RequestBody List<UserShoppingCartOrOrder> uList){
        for(UserShoppingCartOrOrder ele : uList){
            shoppingCartService.deleteFromShoppingCart(ele.getUserId(), ele.getBookId());
        }
        return new ResultMessage(200,"删除购物车成功",null);
    }

    @RequestMapping("order/add")
    public ResultMessage addToOrder(@RequestBody List<UserShoppingCartOrOrder> uList){

        for(UserShoppingCartOrOrder ele : uList){
            orderService.addToOrder(ele.getUserId(), ele.getBookId());
        }
        return new ResultMessage(200,"插入订单成功",true);
//        if( orderService.addToOrder(userId, bookId))
//        else  return new ResultMessage(300,"订单不需要插入了",false);
    }

    @RequestMapping("order/delete")
    public ResultMessage deleteFromOrder(Integer userId,Integer bookId){
        orderService.deleteFromOrder(userId, bookId);
        return new ResultMessage(200,"删除订单成功",null);
    }


}
