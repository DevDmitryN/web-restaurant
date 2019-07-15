package com.serviceSystem.service;

import com.serviceSystem.DAO.DAOImpl.OrderDishDAOImpl;
import com.serviceSystem.DAO.DAOInterface.OrderDishDAO;
import com.serviceSystem.entity.OrderDish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDishService {

    @Autowired
    private OrderDishDAO orderDishDAO;


    public void save(OrderDish orderDish){
        orderDishDAO.save(orderDish);
    }

}
