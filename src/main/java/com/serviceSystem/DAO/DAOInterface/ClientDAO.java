package com.serviceSystem.DAO.DAOInterface;

import com.serviceSystem.entity.Client;


public interface ClientDAO extends BaseDAO<Client> {
    boolean isExist(String phoneNumber,String password);
}
