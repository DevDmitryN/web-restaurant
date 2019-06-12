package com.serviceSystem.DAO.DAOInterface;

import com.serviceSystem.entity.RestaurantTable;

public interface IRestaurantTableDAO extends IBaseDAO<RestaurantTable> {
    void delete(RestaurantTable table);
}
