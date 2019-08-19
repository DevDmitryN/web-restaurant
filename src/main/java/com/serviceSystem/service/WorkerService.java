package com.serviceSystem.service;

import com.serviceSystem.dao.DAOImpl.WorkerDAOImpl;
import com.serviceSystem.dao.DAOInterface.WorkerDAO;
import com.serviceSystem.entity.Worker;
import com.serviceSystem.exception.NoSuchItemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private WorkerDAO workerDAO;

    private WorkerService(){
        workerDAO = new WorkerDAOImpl();
    }
    public List<Worker> getAll(){
        return workerDAO.getAll();
    }
//    public boolean isExist(String email, String password){
//        return workerDAO.isExist(email,password);
//    }
    public Worker getByEmail(String email){
        return workerDAO.getByEmail(email);
    }
    public Worker getById(Integer id){
        Worker worker = workerDAO.getById(id);
        if(worker == null){
            throw new NoSuchItemException("There is no worker with id = " + id);
        }
        return worker;
    }
    public boolean isEmailExist(String email){
        return workerDAO.isEmailExist(email);
    }
    public void save(Worker worker){
        worker.setPassword(encoder.encode(worker.getPassword()));
        workerDAO.save(worker);
    }
    public void updateExceptPassword(Worker updatedWorker){
        Worker client = getById(updatedWorker.getId());
        updatedWorker.setPassword(client.getPassword());
        workerDAO.update(updatedWorker);
    }
    public void updateOnlyPassword(Worker updatedWorker){
        Worker client = getById(updatedWorker.getId());
        client.setPassword(encoder.encode(updatedWorker.getPassword()));
        workerDAO.update(client);
    }
    public Worker getByPhoneNumber(String phoneNumber){
        return workerDAO.getByPhoneNumber(phoneNumber);
    }
    public boolean isPhoneNumberExist(String phoneNumber){
        return workerDAO.isPhoneNumberExist(phoneNumber);
    }

    public List<Worker> getStaff(){
        return workerDAO.getStaff();
    }
}
