package com.serviceSystem.DAO.DAOInterface;

import com.serviceSystem.entity.Dish;

public interface DishDAO extends BaseDAO<Dish> {
    void delete(Dish dish);
}
