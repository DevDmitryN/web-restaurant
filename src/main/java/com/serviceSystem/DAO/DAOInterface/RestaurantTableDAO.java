package com.serviceSystem.DAO.DAOInterface;

import com.serviceSystem.entity.RestaurantTable;

import java.util.List;

public interface RestaurantTableDAO extends BaseDAO<RestaurantTable,Integer> {
    void delete(RestaurantTable table);
    List<RestaurantTable> getFree();
    void updateFreeStatus(int id,boolean status);
}
