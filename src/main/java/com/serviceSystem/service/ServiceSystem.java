package com.serviceSystem.service;

import com.serviceSystem.dao.*;
import com.serviceSystem.entity.*;
import com.serviceSystem.entity.enums.Status;

import java.util.ArrayList;
import java.util.List;


public class ServiceSystem {

    private List<RestaurantTable> tables;
    private List<Dish> dishes;
    private List<Order> orders = new ArrayList<Order>();
//    private Properties properties = new Properties();
    private OrderDaoJDBC orderDaoJDBC;
    private DishDao dishDao;
    private RestaurantTableDao tableDao;
    private ClientDao clientDao;
    private WorkerDao workerDao;

    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String username = "postgres";
    private String password = "root";

    private static ServiceSystem instance;

    private ServiceSystem(){
        init();
    }


    public static ServiceSystem getInstance(){
        if(instance == null){
            instance = new ServiceSystem();
        }
        return instance;
    }

    private void init(){
//        String PATH_TO_PROPERTIES =  getClass().getClassLoader().getResource("dbconfig.properties").getPath();
//        System.out.println(PATH_TO_PROPERTIES);
//        try(FileInputStream fis = new FileInputStream(PATH_TO_PROPERTIES);){
//            properties.load(fis);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
        orderDaoJDBC = new OrderDaoJDBC(url,username,password);
        dishDao = new DishDao();
        tableDao = new RestaurantTableDao();
        clientDao = new ClientDao();
        workerDao = new WorkerDao();
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
        orderDaoJDBC.insert(order);
        orders.add(order);
    }
    public void addClient(Client client){
        clientDao.add(client);
    }
    public void addWorker(Worker worker){
        workerDao.add(worker);
    }
}
