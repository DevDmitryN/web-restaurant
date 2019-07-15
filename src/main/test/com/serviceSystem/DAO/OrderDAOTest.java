package com.serviceSystem.DAO;

import com.serviceSystem.appConfig.ApplicationConfig;
import com.serviceSystem.entity.*;
import com.serviceSystem.entity.enums.Status;
import com.serviceSystem.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class OrderDAOTest {

    @Autowired
    OrderService orderService;
    @Autowired
    ClientService clientService;
    @Autowired
    DishService dishService;
    @Autowired
    TableService tableService;
    @Autowired
    OrderDishService orderDishService;
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
        List<Order> orders = orderService.getNotCompletedByClientId(clientId);
        Order order = orderService.getById(orderId);
        orders.forEach( o -> System.out.println(o));
    }
    @Test
    public void getAll(){
        List<Order> orders = orderService.getAll();
        assertNotNull(orders);
        orders.forEach( order -> assertNotNull(order));

    }
    @Test
    public void geNotTakenWithFreeTables(){
        List<Order> orders = orderService.getNotTakenWithFreeTable();
        orders.forEach( order -> assertNotNull(order));
    }

    @Test
    public void update(){
        Order order = orderService.getById(2);
        System.out.println(order);
        order.setStatus(Status.BEING_PERFORMED);
        orderService.update(order);
    }
    @Test
    public void getById(){
        Long id = 12L;
        Order order = orderService.getById(id);
        assertNotNull(order);
        for (OrderDish orderDish : order.getOrderDish()) {
            assertNotNull(orderDish.getDish());
            assertNotEquals(0, orderDish.getAmount());
        }
    }
    @Test
    public void save(){
        Long clientId = 2L;
        Client client = clientService.getById(clientId);
        Integer dishId = 5;
        Dish dish = dishService.getById(dishId);


        LocalDateTime creationTime = LocalDateTime.now();
        LocalDateTime bookingTime = LocalDateTime.now();
        Integer tableId = 1;
        RestaurantTable table = tableService.getById(tableId);
        Order order = new Order();
        order.setTable(table);
        order.setClient(client);
        order.setCreationTime(creationTime);
        order.setBookingTime(bookingTime);
        order.setOrderDish(Arrays.asList(new OrderDish(order,dish,1)));

        orderService.save(order);
        order.getOrderDish().forEach( orderDish -> orderDishService.save(orderDish));
    }
}

