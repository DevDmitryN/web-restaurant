package com.serviceSystem.service;

import com.serviceSystem.dao.DAOImpl.TableDAOImpl;
import com.serviceSystem.dao.DAOInterface.RestaurantTableDAO;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.exception.NoSuchItemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {
    @Autowired
    private RestaurantTableDAO tableDAO;

    private TableService(){
        tableDAO = new TableDAOImpl();
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
        RestaurantTable table =  tableDAO.getById(id);
        if(table == null){
            throw new NoSuchItemException("There is no table with id = " + id);
        }
        return table;
    }
}
