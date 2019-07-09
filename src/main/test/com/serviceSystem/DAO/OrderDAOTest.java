package com.serviceSystem.DAO;

import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.enums.Status;
import com.serviceSystem.service.OrderService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderDAOTest {
    @Test
    public void updateStatus(){
        long id = 32;
        Order order = OrderService.getInstance().getOrderById(id);
        order.setStatus(Status.BEING_PERFORMED);
        OrderService.getInstance().update(order);
        order = OrderService.getInstance().getOrderById(32);
        assertEquals(Status.BEING_PERFORMED,order.getStatus());
    }
    @Test
    public void getNotCompletedByClientId(){
        long clientId = 2;
        long orderId = 1;
        List<Order> orders = OrderService.getInstance().getNotCompletedByClientId(clientId);
        Order order = OrderService.getInstance().getOrderById(orderId);
        orders.forEach( o -> System.out.println(o));
    }
    @Test
    void getAll(){
        List<Order> orders = OrderService.getInstance().getAll();
        orders.forEach( order -> System.out.println(order));
    }
    @Test
    void getWithFreeTables(){
        List<Order> orders = OrderService.getInstance().getWithFreeTable();
        orders.forEach( order -> System.out.println(order));
    }

    @Test
    void update(){
        Order order = OrderService.getInstance().getOrderById(2);
        System.out.println(order);
        order.setStatus(Status.BEING_PERFORMED);
        OrderService.getInstance().update(order);
    }
}

