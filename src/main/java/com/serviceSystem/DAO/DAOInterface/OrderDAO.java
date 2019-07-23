package com.serviceSystem.DAO.DAOInterface;

import com.serviceSystem.entity.Order;

import java.util.List;

public interface OrderDAO extends BaseDAO<Order,Long> {
    void delete(long id);
    List<Order> getByTable(int tableId);
    List<Order> getActiveByClientId(long clientId);
    List<Order> getNotTakenWithFreeTable();
    Order getById(long id);
}
