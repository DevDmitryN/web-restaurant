package com.serviceSystem.DAO;

import com.serviceSystem.entity.Dish;
import com.serviceSystem.service.DishService;
import org.junit.jupiter.api.Test;

public class DishDAOTest {
    @Test
    void save(){
        Dish dish = new Dish();
        dish.setName("dfasdf");
        dish.setPrice(25);
        DishService.getInstance().save(dish);
    }
//    @Test
//    void delete(){
//        Dish dish = new Dish();
//        dish.setId(5);
//        DishService.getInstance().delete(dish);
//    }
}
