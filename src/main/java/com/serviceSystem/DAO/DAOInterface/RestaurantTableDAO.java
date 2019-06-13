package com.serviceSystem.DAO.DAOInterface;

import com.serviceSystem.entity.RestaurantTable;

public interface RestaurantTableDAO extends BaseDAO<RestaurantTable> {
    void delete(RestaurantTable table);
}
