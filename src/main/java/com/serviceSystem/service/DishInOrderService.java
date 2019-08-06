package com.serviceSystem.service;

import com.serviceSystem.dao.DAOInterface.OrderDishDAO;
import com.serviceSystem.entity.DishInOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishInOrderService {

    @Autowired
    private OrderDishDAO orderDishDAO;


    public void save(DishInOrder dishInOrder){
        orderDishDAO.save(dishInOrder);
    }

}
