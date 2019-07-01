package com.serviceSystem.service;

import com.serviceSystem.DAO.DAOImpl.WorkerDAOImpl;
import com.serviceSystem.DAO.DAOInterface.WorkerDAO;
import com.serviceSystem.entity.Worker;

import java.util.List;

public class WorkerService {
    private WorkerDAO workerDAO;

    private static WorkerService instance;

    public static WorkerService getInstance(){
        if(instance == null){
            instance = new WorkerService();
        }
        return instance;
    }
    private WorkerService(){
        workerDAO = new WorkerDAOImpl();
    }
    public List<Worker> getAll(){
        return workerDAO.getAll();
    }
    public boolean isExist(String phoneNumber, String password){
        return workerDAO.isExist(phoneNumber,password);
    }
}
