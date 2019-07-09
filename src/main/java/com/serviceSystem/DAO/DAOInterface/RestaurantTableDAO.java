package com.serviceSystem.DAO.DAOInterface;

import com.serviceSystem.entity.RestaurantTable;

import java.util.List;

public interface RestaurantTableDAO extends BaseDAO<RestaurantTable> {
    void delete(RestaurantTable table);
    List<RestaurantTable> getFree();
}
