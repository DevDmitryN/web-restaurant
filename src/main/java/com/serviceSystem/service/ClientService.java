package com.serviceSystem.service;

import com.serviceSystem.dao.DAOInterface.ClientDAO;
import com.serviceSystem.entity.Client;
import com.serviceSystem.exception.NoSuchItemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private ClientDAO clientDAO;

    public ClientService(){}

//    public boolean isExist(String email, String password){
//        return clientDAO.isExist(email,password);
//    }
    public Client getByEmail(String email){
        return clientDAO.getByEmail(email);
    }
    public boolean isEmailExist(String email){
        return clientDAO.isEmailExist(email);
    }
    public void save(Client client){
        client.setPassword(encoder.encode(client.getPassword()));
        clientDAO.save(client);
    }
    public List<Client> getAll(){
        return clientDAO.getAll();
    }
    public Client getById(Long id){
        Client client = clientDAO.getById(id);
        if(client == null){
            throw new NoSuchItemException("There is no client with id = " + id);
        }
        return client;
    }
    public boolean isPhoneNumberExist(String phoneNumber){
        return clientDAO.isPhoneNumberExist(phoneNumber);
    }
    public boolean isCardNumberExist(String cardNumber){
        return clientDAO.isCardNumberExist(cardNumber);
    }
}
