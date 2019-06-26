package com.serviceSystem.service;

import com.serviceSystem.DAO.DAOImpl.WorkerDAOImpl;
import com.serviceSystem.DAO.DAOInterface.WorkerDAO;
import com.serviceSystem.entity.Worker;

import java.util.List;

public class WorkerService {
    private WorkerDAO dishDAO;

    private static WorkerService instance;

    public static WorkerService getInstance(){
        if(instance == null){
            instance = new WorkerService();
        }
        return instance;
    }
    private WorkerService(){
        dishDAO = new WorkerDAOImpl();
    }
    public List<Worker> getAll(){
        return dishDAO.getAll();
    }
}
