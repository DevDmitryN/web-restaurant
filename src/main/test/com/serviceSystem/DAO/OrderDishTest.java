package com.serviceSystem.DAO;

import com.serviceSystem.DAO.DAOImpl.OrderDishDAOImpl;
import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.entity.OrderDish;
import com.serviceSystem.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderDishTest {
//    @Test
//    void getById(){
//        OrderDishKey orderDishKey = new OrderDishKey(2L,4);
//        OrderDish orderDish = new OrderDishDAOImpl().getById(orderDishKey);
//        Assertions.assertNotNull(orderDish);
//    }
    @Test
    void save(){
        Long clientId = 2L;
        Client client = ClientService.getInstance().getById(clientId);
        Integer dishId = 5;
        Dish dish = DishService.getInstance().getById(dishId);


        LocalDateTime creationTime = LocalDateTime.now();
        LocalDateTime bookingTime = LocalDateTime.now();
        Integer tableId = 1;
        RestaurantTable table = TableService.getInstance().getById(tableId);
        Order order = new Order();
        order.setTable(table);
        order.setClient(client);
        order.setCreationTime(creationTime);
        order.setBookingTime(bookingTime);

        order.setId(OrderService.getInstance().save(order));

        OrderDish orderDish = new OrderDish();
        orderDish.setOrder(order);
        orderDish.setDish(dish);
        orderDish.setAmount(1);

        OrderDishService.getInstance().save(orderDish);
    }
}
