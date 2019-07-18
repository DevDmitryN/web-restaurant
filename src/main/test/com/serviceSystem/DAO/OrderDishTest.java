package com.serviceSystem.DAO;

import com.serviceSystem.DAO.DAOImpl.OrderDishDAOImpl;
import com.serviceSystem.DAO.DAOInterface.DishDAO;
import com.serviceSystem.DAO.DAOInterface.OrderDAO;
import com.serviceSystem.DAO.DAOInterface.OrderDishDAO;
import com.serviceSystem.appConfig.ApplicationConfig;
import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.entity.OrderDish;
import com.serviceSystem.entity.enums.Status;
import com.serviceSystem.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class OrderDishTest {

    @Autowired
    OrderDAO orderDAO;
    @Autowired
    DishDAO dishDAO;
    @Autowired
    OrderDishDAO orderDishDAO;

    public void save(){
        Order order = orderDAO.getById(7L);
        Dish dish1 = dishDAO.getById(5);
        Dish dish2 = dishDAO.getById(7);
    }
}
