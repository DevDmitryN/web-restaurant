package com.serviceSystem.DAO;

import com.serviceSystem.DAO.DAOImpl.OrderDishDAOImpl;
import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.entity.OrderDish;
import com.serviceSystem.entity.enums.Status;
import com.serviceSystem.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderDishTest {

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
    @Test
    void getNotTakenWithFreeTables(){
        List<Order> orders = OrderService.getInstance().getNotTakenWithFreeTable();
        for (Order order : orders) {
            assertNotNull(order);
            assertEquals(true,order.getTable().getFreeStatus());
            assertEquals(Status.NOT_TAKEN,order.getStatus());
        }
    }
}
