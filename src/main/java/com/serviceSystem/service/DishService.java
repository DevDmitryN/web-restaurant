package com.serviceSystem.service;

import com.serviceSystem.dao.DAOInterface.DishDAO;
import com.serviceSystem.entity.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    @Autowired
    private DishDAO dishDAO;

    public List<Dish> getAll(){
        return dishDAO.getAll();
    }
    public Dish getById(Integer id){
        return dishDAO.getById(id);
    }
    public void save(Dish dish){
        dishDAO.save(dish);
    }
    public List<Dish> getWhichAreInMenu(){
        return dishDAO.getWhichAreInMenu();
    }
//    public void delete(Dish dish){
//        dishDAO.delete(dish);
//    }
}
