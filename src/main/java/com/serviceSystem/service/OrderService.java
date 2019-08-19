package com.serviceSystem.service;

import com.serviceSystem.dao.DAOInterface.OrderDAO;
import com.serviceSystem.dao.DAOInterface.DishInOrderDao;
import com.serviceSystem.entity.DishInOrder;
import com.serviceSystem.entity.Notification;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.Worker;
import com.serviceSystem.entity.enums.Status;
import com.serviceSystem.exception.ActiveOrderNotFoundException;
import com.serviceSystem.exception.NoSuchItemException;
import com.serviceSystem.exception.OrderAlreadyTakenException;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class OrderService {
    private Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private DishInOrderDao dishInOrderDao;
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;


    public List<Order> getAll(){
        return orderDAO.getAll();
    }
    public List<Order> getByTableId(int id){
        return orderDAO.getByTable(id);
    }
    @Transactional
    public void save(Order order){
//        orderDAO.save(order);
//        for (DishInOrder orderDish : order.getDishesInOrder()) {
//            orderDish.setOrder(order);
//            dishInOrderDao.save(orderDish);
//        }
        logger.info("New order have come!");
        messagingTemplate.convertAndSendToUser("boris@britva.com", "/topic/notification",new Notification(LocalTime.now(),"New order"));
    }

    @Transactional
    public void delete(long id){
        orderDAO.delete(getById(id));
    }

    @Transactional
    public Order getById(long id){
        Order order = orderDAO.getById(id);
        if(order == null){
            throw new NoSuchItemException("There is no order with id = " + id);
        }
        Hibernate.initialize(order.getDishesInOrder());
        return order;
    }

    public void update(Order order){
        orderDAO.update(order);
    }


    public List<Order> getActiveOrNotByClientId(long clientId, boolean isActive){
        List<Order> orders = orderDAO.getActiveOrNotByClientId(clientId,isActive);
//        Hibernate.initialize(orders);

        return orders;
    }
//    public List<Order> getNotTakenWithFreeTable(){
//        return orderDAO.getNotTakenWithFreeTable();
//    }

    @Transactional
    public void changeWorkerForOrder(Order order,Worker worker) throws OrderAlreadyTakenException {
        if(order.getWorker() != null){
            if(!order.getWorker().getEmail().equals(worker.getEmail())){
                throw new OrderAlreadyTakenException("Order already taken");
            }
            order.setWorker(null);
            order.setStatus(Status.NOT_TAKEN);
        }else{
            order.setWorker(worker);
            order.setStatus(Status.BEING_PERFORMED);
        }
        update(order);
    }
    public List<Order> getActiveForTable(int tableId){
        return orderDAO.getActiveForTable(tableId);
    }

    @Transactional
    public void updateDishesInOrder(Order order){
        dishInOrderDao.deleteAllByOrderId(order.getId());
        for (DishInOrder dishInOrder : order.getDishesInOrder()) {
            dishInOrder.setOrder(order);
            dishInOrderDao.save(dishInOrder);
        }
    }
    public boolean isBookingPeriodValidForTable(LocalDateTime begin, LocalDateTime end, int tableId){
        return orderDAO.isBookingPeriodValidForTable(begin,end,tableId);
    }
    public Order getActiveById(long id){
        Order order = orderDAO.getActiveById(id);
        if(order == null){
            throw new ActiveOrderNotFoundException("Order " + id + " isn't active or doesn't exist");
        }
        return order;
    }
}
