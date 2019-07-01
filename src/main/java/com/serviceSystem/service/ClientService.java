package com.serviceSystem.service;

import com.serviceSystem.DAO.DAOImpl.ClientDAOImpl;
import com.serviceSystem.DAO.DAOInterface.ClientDAO;
import com.serviceSystem.DAO.DAOInterface.OrderDAO;
import com.serviceSystem.entity.Client;

public class ClientService {
    private ClientDAO clientDAO;

    private static ClientService instance;

    public static ClientService getInstance(){
        if(instance == null){
            instance = new ClientService();
        }
        return instance;
    }

    private ClientService(){
        clientDAO = new ClientDAOImpl();
    }
    public boolean isExist(String phoneNumber,String password){
        return clientDAO.isExist(phoneNumber,password);
    }
}
