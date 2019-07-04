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
        long clientId = 1;
        long orderId = 32;
        List<Order> orders = OrderService.getInstance().getNotCompletedByClientId(clientId);
        Order order = OrderService.getInstance().getOrderById(orderId);
        System.out.println(orders.get(0));
        System.out.println(order);
    }
}

