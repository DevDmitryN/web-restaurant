package com.serviceSystem.DAO.DAOInterface;

import com.serviceSystem.entity.Dish;

public interface IDishDAO extends IBaseDAO<Dish> {
    void delete(Dish dish);
}
