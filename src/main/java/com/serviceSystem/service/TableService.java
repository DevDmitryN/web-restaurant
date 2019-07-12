package com.serviceSystem.service;

import com.serviceSystem.DAO.DAOImpl.RestaurantTableDAOImpl;
import com.serviceSystem.DAO.DAOInterface.RestaurantTableDAO;
import com.serviceSystem.entity.RestaurantTable;

import java.util.List;

public class TableService {
    private RestaurantTableDAO tableDAO;

    private static TableService instance;

    public static TableService getInstance(){
        if(instance == null){
            instance = new TableService();
        }
        return instance;
    }
    private TableService(){
        tableDAO = new RestaurantTableDAOImpl();
    }
    public List<RestaurantTable> getAll(){
        return tableDAO.getAll();
    }
    public void update(RestaurantTable table){
        tableDAO.update(table);
    }
    public List<RestaurantTable> getFree(){
        return tableDAO.getFree();
    }
    public void save(RestaurantTable table){
        tableDAO.save(table);
    }
    public RestaurantTable getById(Integer id){
        return tableDAO.getById(id);
    }
}
