package com.serviceSystem.service;

import com.serviceSystem.dao.DishDao;
import com.serviceSystem.dao.OrderDao;
import com.serviceSystem.dao.RestaurantTableDao;
import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.entity.enums.Status;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class ServiceSystem {

    private List<RestaurantTable> tables;
    private List<Dish> dishes;
    private List<Order> orders = new ArrayList<Order>();
    private Properties properties = new Properties();
    private final String PATH_TO_PROPERTIES = "src/main/resources/dbconfig.properties";
    private OrderDao orderDao;
    private DishDao dishDao;
    private RestaurantTableDao tableDao;

    public ServiceSystem(){
        init();
    }

    private void init(){
        try(FileInputStream fis = new FileInputStream(PATH_TO_PROPERTIES)){
            properties.load(fis);
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(properties.toString());
        orderDao = new OrderDao(properties);
        dishDao = new DishDao();
        tableDao = new RestaurantTableDao();
        initDishes();
        initTables();
    }
    private void initDishes(){
        dishes = dishDao.getDishes();
    }
    public void addDish(Dish dish){
        dishDao.insert(dish);
    }
    private void initTables(){
        tables = tableDao.getRestaurantTables();
    }
    private void initOrders(){

    }
    public List<RestaurantTable> getTables() {
        return tables;
    }

    public List<Dish> getDishes() {
        return dishes;
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void updateDishes(){
        //some code
    }
    public void changeOrderStatus(Order order, Status status){
        orders.get(orders.indexOf(order)).setStatus(status);
        //updating order in the db
    }
    public void takeOrder(Order order){
        orderDao.insert(order);
        orders.add(order);
    }
}
