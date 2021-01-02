package com.example.controller;

import com.example.service.OrderService;
import com.example.service.ShoppingCartService;
import com.example.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultMessage deleteFromShoppingCart(Integer userId,Integer bookId){
        shoppingCartService.deleteFromShoppingCart(userId, bookId);
        return new ResultMessage(200,"删除购物车成功",null);
    }

    @RequestMapping("order/add")
    public ResultMessage addToOrder(Integer userId,Integer bookId){
        if( orderService.addToOrder(userId, bookId))
            return new ResultMessage(200,"插入订单成功",true);
        else  return new ResultMessage(300,"订单不需要插入了",false);

    }

    @RequestMapping("order/delete")
    public ResultMessage deleteFromOrder(Integer userId,Integer bookId){
        orderService.deleteFromOrder(userId, bookId);
        return new ResultMessage(200,"删除订单成功",null);
    }


}
