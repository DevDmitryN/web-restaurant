package com.serviceSystem.DAO;

import com.serviceSystem.DAO.DAOImpl.OrderDishDAOImpl;
import com.serviceSystem.appConfig.ApplicationConfig;
import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.entity.OrderDish;
import com.serviceSystem.entity.enums.Status;
import com.serviceSystem.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class OrderDishTest {


}
