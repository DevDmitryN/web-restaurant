package com.serviceSystem.service;

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
        orderDAO = new OrderDaoJDBCImpl();
    }

    public List<Order> getAll(){
        return orderDAO.getAll();
    }
    public List<Order> getOrdersByTableId(int id){
        return orderDAO.getOrdersByTable(id);
    }
    public void save(Order order){
        orderDAO.save(order);
    }
    public void delete(long id){
        orderDAO.delete(id);
    }
    public Order getOrderById(long id){
        return orderDAO.getById(id);
    }
    public void update(Order order){
        orderDAO.update(order);
    }
}
