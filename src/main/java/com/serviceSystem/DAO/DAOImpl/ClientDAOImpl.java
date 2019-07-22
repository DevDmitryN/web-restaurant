package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.ClientDAO;
import com.serviceSystem.entity.Client;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ClientDAOImpl extends UserDAOImpl<Client,Long> implements ClientDAO {

    public ClientDAOImpl(){
        super(Client.class);
    }

    @Override
    @Transactional
    public boolean isCardNumberExist(String cardNumber) {
        String getByCardNumber = "from com.serviceSystem.entity.Client c where c.cardNumber = :cardNumber";
        Query query = getCurrentSession().createQuery(getByCardNumber);
        query.setParameter("cardNumber",cardNumber);
        return query.list().size() != 0;
    }
}
