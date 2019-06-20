package com.serviceSystem.service;

import com.serviceSystem.DAO.DAOImpl.DishDAOImpl;
import com.serviceSystem.DAO.DAOInterface.DishDAO;
import com.serviceSystem.entity.Dish;

import java.util.List;

public class DishService {
    private DishDAO dishDAO;

    private static DishService instance;

    public static DishService getInstance(){
        if(instance == null){
            instance = new DishService();
        }
        return instance;
    }
    DishService(){
        dishDAO = new DishDAOImpl();
    }
    public List<Dish> getAll(){
        return dishDAO.getAll();
    }
}
