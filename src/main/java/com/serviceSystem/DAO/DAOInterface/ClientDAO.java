package com.serviceSystem.DAO.DAOInterface;

import com.serviceSystem.entity.Client;


public interface ClientDAO extends UserDAO<Client,Long> {
    boolean isCardNumberExist(String cardNumber);
}
