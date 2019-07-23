package com.serviceSystem.service;

import com.serviceSystem.DAO.DAOImpl.OrderDAOImpl;
import com.serviceSystem.DAO.DAOInterface.OrderDAO;
import com.serviceSystem.DAO.DAOInterface.OrderDishDAO;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Order> getNotCompletedByClientId(long clientId){
        return orderDAO.getActiveByClientId(clientId);
    }
    public List<Order> getNotTakenWithFreeTable(){
        return orderDAO.getNotTakenWithFreeTable();
    }
    public void changeWorkerForOrder(long orderId,Worker worker){
        Order order = getById(orderId);
        if(order.getWorker() != null){
            order.setWorker(null);
        }else{
            order.setWorker(worker);
        }
        update(order);
    }
}
