package com.serviceSystem.DAO;

import com.serviceSystem.appConfig.ApplicationConfig;
import com.serviceSystem.entity.Dish;
import com.serviceSystem.service.DishService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class DishDAOTest {
    @Autowired
    DishService dishService;
    @Test
    public void save(){
        Dish dish = new Dish();
        dish.setName("dfasdf");
        dish.setPrice(25);
        dishService.save(dish);
    }
//    @Test
//    void delete(){
//        Dish dish = new Dish();
//        dish.setId(5);
//        DishService.getInstance().delete(dish);
//    }
}
