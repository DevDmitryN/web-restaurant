package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.ClientDAO;
import com.serviceSystem.entity.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDAOImpl extends UserDAOImpl<Client,Long> implements ClientDAO {
    private final String GET_BY_EMAIL_AND_PASSWORD = "from com.serviceSystem.entity.Client c where c.email like :email and c.password like :password";
    private final String GET_BY_EMAIL = "from com.serviceSystem.entity.Client c where c.email like :email";

    public ClientDAOImpl(){
        super(Client.class);
    }

}
