package com.serviceSystem.service;

import com.serviceSystem.DAO.DAOImpl.OrderDishDAOImpl;
import com.serviceSystem.entity.OrderDish;

public class OrderDishService {
    private static OrderDishService instance;

    private OrderDishDAOImpl orderDishDAO;

    public OrderDishService() {
        orderDishDAO = new OrderDishDAOImpl();
    }

    public static OrderDishService getInstance(){
        if(instance == null){
            instance = new OrderDishService();
        }
        return instance;
    }

    public void save(OrderDish orderDish){
        orderDishDAO.save(orderDish);
    }

}
