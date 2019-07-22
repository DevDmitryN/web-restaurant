package com.serviceSystem.DAO.DAOInterface;

import com.serviceSystem.entity.Dish;

import java.util.List;

public interface DishDAO extends BaseDAO<Dish,Integer> {
    List<Dish> getWhichAreInMenu();
}
