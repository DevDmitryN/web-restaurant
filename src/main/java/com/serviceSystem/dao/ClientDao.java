package com.serviceSystem.dao;

import com.serviceSystem.entity.Client;
import com.serviceSystem.hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientDao {
    public void add(Client client){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(client);
        transaction.commit();
        session.close();
    }
    public Client getClientById(long id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Client client = session.get(Client.class,id);
        transaction.commit();
        session.close();
        return client;
    }
}
