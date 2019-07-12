package com.serviceSystem.service;

import com.serviceSystem.DAO.DAOImpl.WorkerDAOImpl;
import com.serviceSystem.DAO.DAOInterface.WorkerDAO;
import com.serviceSystem.entity.Worker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public boolean isExist(String email, String password){
        return workerDAO.isExist(email,password);
    }
    public Worker getByEmail(String email){
        return workerDAO.getByEmail(email);
    }
    public Worker getById(Integer id){
        return workerDAO.getById(id);
    }
    public boolean isEmailExist(String email){
        return workerDAO.isEmailExist(email);
    }
    public void save(Worker worker){
        workerDAO.save(worker);
    }
}
