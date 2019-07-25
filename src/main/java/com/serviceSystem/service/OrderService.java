package com.serviceSystem.service;

import com.serviceSystem.DAO.DAOImpl.OrderDAOImpl;
import com.serviceSystem.DAO.DAOInterface.OrderDAO;
import com.serviceSystem.DAO.DAOInterface.OrderDishDAO;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.Worker;
import com.serviceSystem.entity.enums.Status;
import com.serviceSystem.exception.OrderAlreadyTakenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderDishDAO orderDishDAO;

    public List<Order> getAll(){
        return orderDAO.getAll();
    }
    public List<Order> getByTableId(int id){
        return orderDAO.getByTable(id);
    }
    public void save(Order order){
        orderDAO.save(order);
        order.getOrderDish().forEach( orderDish -> orderDishDAO.save(orderDish));
    }
    public void delete(long id){
        orderDAO.delete(id);
    }
    public Order getById(long id){
        return orderDAO.getById(id);
    }
    public void update(Order order){
        orderDAO.update(order);
    }
    @Transactional
    public List<Order> getNotCompletedByClientId(long clientId){
        List<Order> orders = orderDAO.getActiveByClientId(clientId);
        for (Order order : orders) {
            order.setOrderDish(order.getOrderDish());
        }
        return orders;
    }
    public List<Order> getNotTakenWithFreeTable(){
        return orderDAO.getNotTakenWithFreeTable();
    }

    @Transactional
    public void changeWorkerForOrder(long orderId,Worker worker) throws OrderAlreadyTakenException {
        Order order = getById(orderId);
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

//    public List<Order> getNotTakenForTable(int tableId){
//        return orderDAO.getNotTakenForTable(tableId);
//    }
}
