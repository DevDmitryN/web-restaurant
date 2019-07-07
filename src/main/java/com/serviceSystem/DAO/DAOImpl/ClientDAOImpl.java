package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.ClientDAO;
import com.serviceSystem.entity.Client;
import com.serviceSystem.hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    private final String GET_BY_EMAIL_AND_PASSWORD = "from com.serviceSystem.entity.Client c where c.email like :email and c.password like :password";
    private final String GET_BY_EMAIL = "from com.serviceSystem.entity.Client c where c.email like :email";
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
        Query query = session.createQuery("FROM com.serviceSystem.entity.Client");
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
    @Override
    public boolean isExist(String email, String password) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery(GET_BY_EMAIL_AND_PASSWORD);
        query.setParameter("email",email);
        query.setParameter("password",password);
        List<Client> client = query.list();
        return client.size() == 0 ? false : true;
    }
    @Override
    public Client getByEmail(String email){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery(GET_BY_EMAIL);
        query.setParameter("email",email);
        return (Client) query.list().get(0);
    }

    @Override
    public boolean isEmailExist(String email) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery(GET_BY_EMAIL);
        query.setParameter("email",email);
        return query.list().size() == 0 ? false : true;
    }
}
