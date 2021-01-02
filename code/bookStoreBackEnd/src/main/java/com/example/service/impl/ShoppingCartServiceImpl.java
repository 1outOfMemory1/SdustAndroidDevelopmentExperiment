package com.example.service.impl;

import com.example.dao.ShoppingCartDao;
import com.example.service.ShoppingCartService;
import org.apache.ibatis.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Override
    public Boolean addToShoppingCart(Integer userId, Integer bookId) {
        try {
            shoppingCartDao.addToShoppingCart(userId, bookId);
        } catch (DuplicateKeyException e) {
            //e.printStackTrace();

            return false;
        }
        return true;
    }

    @Override
    public void deleteFromShoppingCart(Integer userId, Integer bookId) {
        shoppingCartDao.deleteFromShoppingCart(userId, bookId);
    }
}
