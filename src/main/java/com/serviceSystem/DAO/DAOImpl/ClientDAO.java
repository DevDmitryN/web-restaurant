package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.IClientDAO;
import com.serviceSystem.entity.Client;
import com.serviceSystem.hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ClientDAO implements IClientDAO {
    @Override
    public void save(Client client){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(client);
        transaction.commit();
        session.close();
    }
    @Override
    public Client getById(long id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Client client = session.get(Client.class,id);
        session.close();
        return client;
    }
    @Override
    public List<Client> getAll(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Client");
        List<Client> clients = (List<Client>) query.list();
        session.close();
        return clients;
    }
    @Override
    public void update(Client client){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(client);
        transaction.commit();
        session.close();
    }

//    public void delete(Client client){
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.delete(client);
//        transaction.commit();
//        session.close();
//    }
}
