package com.serviceSystem.service;

import com.serviceSystem.dao.DAOInterface.OrderDAO;
import com.serviceSystem.dao.DAOInterface.DishInOrderDao;
import com.serviceSystem.entity.DishInOrder;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.Worker;
import com.serviceSystem.entity.enums.Status;
import com.serviceSystem.exception.NoSuchItemException;
import com.serviceSystem.exception.OrderAlreadyTakenException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private DishInOrderDao dishInOrderDao;
    @Autowired
    private BookingService bookingService;

    public List<Order> getAll(){
        return orderDAO.getAll();
    }
    public List<Order> getByTableId(int id){
        return orderDAO.getByTable(id);
    }
    @Transactional
    public void save(Order order){
        orderDAO.save(order);
        bookingService.save(order);
        for (DishInOrder orderDish : order.getDishesInOrder()) {
            orderDish.setOrder(order);
            dishInOrderDao.save(orderDish);
        }
    }
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

    @Transactional
    public List<Order> getActiveByClientId(long clientId){
        List<Order> orders = orderDAO.getActiveByClientId(clientId);
        for (Order order : orders) {
            Hibernate.initialize(order.getDishesInOrder());
        }
        return orders;
    }
    public List<Order> getNotTakenWithFreeTable(){
        return orderDAO.getNotTakenWithFreeTable();
    }

    @Transactional
    public void changeWorkerForOrder(Order order,Worker worker) throws OrderAlreadyTakenException {
        if(order.getWorker() != null){
            if(!order.getWorker().getEmail().equals(worker.getEmail())){
                throw new OrderAlreadyTakenException();
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

    public void updateDishesInOrder(Order order){
        dishInOrderDao.deleteAllByOrderId(order.getId());
        for (DishInOrder dishInOrder : order.getDishesInOrder()) {
            dishInOrderDao.save(dishInOrder);
        }
    }
//    public boolean isBookingPeriodValidForTable(LocalDateTime begin, LocalDateTime end,int tableId){
//        return orderDAO.isBookingPeriodValidForTable(begin,end,tableId);
//    }
}
