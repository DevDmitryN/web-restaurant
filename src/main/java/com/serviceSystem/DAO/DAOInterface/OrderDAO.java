package com.serviceSystem.DAO.DAOInterface;

import com.serviceSystem.entity.Order;

import java.util.List;

public interface OrderDAO extends BaseDAO<Order> {
    void delete(long id);
    List<Order> getOrdersByTable(int tableId);
}
