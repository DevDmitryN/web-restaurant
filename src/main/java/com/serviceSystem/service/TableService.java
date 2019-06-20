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
    TableService(){
        tableDAO = new RestaurantTableDAOImpl();
    }
    public List<RestaurantTable> getAll(){
        return tableDAO.getAll();
    }
}
