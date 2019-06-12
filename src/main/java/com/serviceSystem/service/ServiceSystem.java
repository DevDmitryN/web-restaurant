package com.serviceSystem.service;

import com.serviceSystem.DAO.DAOImpl.*;
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
    private DishDAO dishDao;
    private RestaurantTableDAO tableDao;
    private ClientDAO clientDao;
    private WorkerDAO workerDao;

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
        dishDao = new DishDAO();
        tableDao = new RestaurantTableDAO();
        clientDao = new ClientDAO();
        workerDao = new WorkerDAO();
        initDishes();
        initTables();
    }
    private void initDishes(){
        dishes = dishDao.getAll();
    }
    public void addDish(Dish dish){
        dishDao.save(dish);
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
        orderDaoJDBC.save(order);
        orders.add(order);
    }
    public void addClient(Client client){
        clientDao.save(client);
    }
    public void addWorker(Worker worker){
        workerDao.save(worker);
    }
}
