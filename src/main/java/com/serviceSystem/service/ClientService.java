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
    public Client getByPhoneNumber(String phoneNumber){
        return clientDAO.getByPhoneNumber(phoneNumber);
    }
    public Client getByCardNumber(String cardNumber){
        return clientDAO.getByCardNumber(cardNumber);
    }
    public boolean isCardNumberExist(String cardNumber){
        return clientDAO.isCardNumberExist(cardNumber);
    }
    public void updateExceptPassword(Client updatedClient){
        Client client = getById(updatedClient.getId());
        updatedClient.setPassword(client.getPassword());
        clientDAO.update(updatedClient);
    }
    public void updateOnlyPassword(Client updatedClient){
        Client client = getById(updatedClient.getId());
        client.setPassword(encoder.encode(updatedClient.getPassword()));
        clientDAO.update(client);
    }

}
