package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.ClientDAO;
import com.serviceSystem.entity.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDAOImpl extends UserDAOImpl<Client,Long> implements ClientDAO {

    public ClientDAOImpl(){
        super(Client.class);
    }

}
