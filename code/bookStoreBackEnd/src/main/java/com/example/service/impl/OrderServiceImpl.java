package com.example.service.impl;

import com.example.dao.OrderDao;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;


    @Override
    public Boolean addToOrder(Integer userId, Integer bookId) {
        try {
            orderDao.addToOrder(userId, bookId);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void deleteFromOrder(Integer userId, Integer bookId) {
        orderDao.deleteFromOrder(userId, bookId);
    }
}
