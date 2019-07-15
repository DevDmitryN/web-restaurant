package com.serviceSystem.service;

import com.serviceSystem.DAO.DAOImpl.ClientDAOImpl;
import com.serviceSystem.DAO.DAOInterface.ClientDAO;
import com.serviceSystem.DAO.DAOInterface.OrderDAO;
import com.serviceSystem.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientDAO clientDAO;

    public ClientService(){}

    public boolean isExist(String email, String password){
        return clientDAO.isExist(email,password);
    }
    public Client getByEmail(String email){
        return clientDAO.getByEmail(email);
    }
    public boolean isEmailExist(String email){
        return clientDAO.isEmailExist(email);
    }
    public void save(Client client){
        clientDAO.save(client);
    }
    public List<Client> getAll(){
        return clientDAO.getAll();
    }
    public Client getById(Long id){
        return clientDAO.getById(id);
    }
}
