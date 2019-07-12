package com.serviceSystem.service;

import com.serviceSystem.DAO.DAOImpl.DishDAOImpl;
import com.serviceSystem.DAO.DAOInterface.DishDAO;
import com.serviceSystem.entity.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    @Autowired
    private DishDAO dishDAO;

    private static DishService instance;

    public static DishService getInstance(){
        if(instance == null){
            instance = new DishService();
        }
        return instance;
    }
    private DishService(){
        dishDAO = new DishDAOImpl();
    }

    public List<Dish> getAll(){
        return dishDAO.getAll();
    }
    public Dish getById(Integer id){
        return dishDAO.getById(id);
    }
    public void save(Dish dish){
        dishDAO.save(dish);
    }
//    public void delete(Dish dish){
//        dishDAO.delete(dish);
//    }
}
