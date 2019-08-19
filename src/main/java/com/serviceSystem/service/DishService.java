package com.serviceSystem.service;

import com.serviceSystem.dao.DAOInterface.DishDAO;
import com.serviceSystem.entity.Dish;
import com.serviceSystem.exception.NoSuchItemException;
import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    private Logger logger = LoggerFactory.getLogger(DishService.class);

    @Autowired
    private DishDAO dishDAO;

    public List<Dish> getAll(){
        return dishDAO.getAll();
    }
    public Dish getById(Integer id){
        Dish dish = dishDAO.getById(id);
        if(dish == null){
            logger.warn("Dish " + id + " not found");
            throw new NoSuchItemException("There is no dish with id = " + id);
        }
        return dish;
    }
    public void save(Dish dish){
        dishDAO.save(dish);
    }
    public List<Dish> getWhichAreInMenu(){
        return dishDAO.getWhichAreInMenu();
    }
    public void update(Dish dish){
        try{
            dishDAO.update(dish);
        }catch (HibernateOptimisticLockingFailureException e){
            logger.warn("Dish " + dish.getId() + " not found");
            throw new NoSuchItemException("There is no dish with id = " + dish.getId());
        }

    }

    public Dish getByIdFromMenu(int dishId) {
        return dishDAO.getByIdFromMenu(dishId);
    }
//    public void delete(Dish dish){
//        dishDAO.delete(dish);
//    }
}
