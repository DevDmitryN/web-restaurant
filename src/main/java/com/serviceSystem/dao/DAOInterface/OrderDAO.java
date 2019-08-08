package com.serviceSystem.dao.DAOInterface;

import com.serviceSystem.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderDAO extends BaseDAO<Order,Long> {
    void delete(Order order);
    List<Order> getByTable(int tableId);
    List<Order> getActiveOrNotByClientId(long clientId, boolean isActive);
//    List<Order> getNotTakenWithFreeTable();
    List<Order> getActiveForTable(int tableId);
    boolean isBookingPeriodValidForTable(LocalDateTime begin,LocalDateTime end, int tableId);
    Order getActiveById(long id);
//    List<Order> getNotTakenForTable(int tableId);
}
