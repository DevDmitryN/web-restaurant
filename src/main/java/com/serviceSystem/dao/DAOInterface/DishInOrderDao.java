package com.serviceSystem.dao.DAOInterface;

import com.serviceSystem.entity.DishInOrder;

import java.util.List;

public interface DishInOrderDao extends BaseDAO<DishInOrder, Long> {
    void deleteAllByOrderId(long orderId);
    List<DishInOrder> getByOrderId(long orderId);
}
