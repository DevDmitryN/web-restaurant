package com.serviceSystem.DAO;

import com.serviceSystem.entity.*;
import com.serviceSystem.entity.enums.Status;
import com.serviceSystem.service.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderDAOTest {

//    public void updateStatus(){
//        long id = 32;
//        Order order = OrderService.getInstance().getById(id);
//        order.setStatus(Status.BEING_PERFORMED);
//        OrderService.getInstance().update(order);
//        order = OrderService.getInstance().getById(32);
//        assertEquals(Status.BEING_PERFORMED,order.getStatus());
//    }
    @Test
    public void getNotCompletedByClientId(){
        long clientId = 2;
        long orderId = 1;
        List<Order> orders = OrderService.getInstance().getNotCompletedByClientId(clientId);
        Order order = OrderService.getInstance().getById(orderId);
        orders.forEach( o -> System.out.println(o));
    }
    @Test
    void getAll(){
        List<Order> orders = OrderService.getInstance().getAll();
        assertNotNull(orders);
        orders.forEach( order -> assertNotNull(order));

    }
    @Test
    void getWithFreeTables(){
        List<Order> orders = OrderService.getInstance().getNotTakenWithFreeTable();
        orders.forEach( order -> assertNotNull(order));
    }

    @Test
    void update(){
        Order order = OrderService.getInstance().getById(2);
        System.out.println(order);
        order.setStatus(Status.BEING_PERFORMED);
        OrderService.getInstance().update(order);
    }
    @Test
    void getById(){
        Long id = 12L;
        Order order = OrderService.getInstance().getById(id);
        assertNotNull(order);
        for (OrderDish orderDish : order.getOrderDish()) {
            assertNotNull(orderDish.getDish());
            assertNotEquals(0, orderDish.getAmount());
        }
    }
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
        order.setOrderDish(Arrays.asList(new OrderDish(order,dish,1)));

        OrderService.getInstance().save(order);
        order.getOrderDish().forEach( orderDish -> OrderDishService.getInstance().save(orderDish));
    }
}

