package com.serviceSystem.service;

import com.serviceSystem.DAO.DAOImpl.OrderDAOImpl;
import com.serviceSystem.DAO.DAOImpl.OrderDaoJDBCImpl;
import com.serviceSystem.DAO.DAOInterface.OrderDAO;
import com.serviceSystem.entity.Order;

import java.util.List;

public class OrderService {

    private OrderDAO orderDAO;

    private static OrderService instance;

    public static OrderService getInstance(){
        if(instance == null){
            instance = new OrderService();
        }
        return instance;
    }

    private OrderService(){
        orderDAO = new OrderDAOImpl();
    }

    public List<Order> getAll(){
        return orderDAO.getAll();
    }
    public List<Order> getByTableId(int id){
        return orderDAO.getByTable(id);
    }
    public Long save(Order order){
        return orderDAO.save(order);
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
    public List<Order> getWithFreeTable(){
        return orderDAO.getWithFreeTable();
    }
}
