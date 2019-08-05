package com.serviceSystem.dao.DAOImpl;

import com.serviceSystem.dao.DAOInterface.ClientDAO;
import com.serviceSystem.entity.Client;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ClientDAOImpl extends UserDAOImpl<Client,Long> implements ClientDAO {

    public ClientDAOImpl(){
        super(Client.class);
    }


    @Override
    @Transactional
    public Client getByCardNumber(String cardNumber) {
        String hql = "from com.serviceSystem.entity.Client c where c.cardNumber = :cardNumber";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("cardNumber",cardNumber);
        return (Client) query.uniqueResult();
    }

    @Override
    @Transactional
    public boolean isCardNumberExist(String cardNumber) {
        return getByCardNumber(cardNumber) != null;
    }
}
