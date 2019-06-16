package com.serviceSystem.service;

import com.serviceSystem.DAO.DAOImpl.*;
import com.serviceSystem.DAO.DAOInterface.DishDAO;
import com.serviceSystem.DAO.DAOInterface.OrderDAO;
import com.serviceSystem.entity.*;
import com.serviceSystem.entity.enums.Status;

import java.util.ArrayList;
import java.util.List;


public class ServiceSystem {

    private List<RestaurantTable> tables;
    private List<Dish> dishes;
    private List<Order> orders = new ArrayList<Order>();
//    private Properties properties = new Properties();
    private OrderDAO orderDao;
    private DishDAO dishDaoImpl;
    private RestaurantTableDAOImpl tableDao;
    private ClientDAOImpl clientDaoImpl;
    private WorkerDAOImpl workerDaoImpl;

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
        orderDao = new OrderDaoJDBCImpl();
        dishDaoImpl = new DishDAOImpl();
        tableDao = new RestaurantTableDAOImpl();
        clientDaoImpl = new ClientDAOImpl();
        workerDaoImpl = new WorkerDAOImpl();
        initDishes();
        initTables();
    }
    private void initDishes(){
        dishes = dishDaoImpl.getAll();
    }
    public void addDish(Dish dish){
        dishDaoImpl.save(dish);
    }
    private void initTables(){
        tables = tableDao.getAll();
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
        orderDao.save(order);
        orders.add(order);
    }
    public void addClient(Client client){
        clientDaoImpl.save(client);
    }
    public void addWorker(Worker worker){
        workerDaoImpl.save(worker);
    }
}
