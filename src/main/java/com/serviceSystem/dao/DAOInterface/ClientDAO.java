package com.serviceSystem.dao.DAOInterface;

import com.serviceSystem.entity.Client;


public interface ClientDAO extends UserDAO<Client,Long> {
    Client getByCardNumber(String cardNumber);
    boolean isCardNumberExist(String cardNumber);
}
