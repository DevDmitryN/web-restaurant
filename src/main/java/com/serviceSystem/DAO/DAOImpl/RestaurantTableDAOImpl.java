package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.RestaurantTableDAO;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RestaurantTableDAOImpl implements RestaurantTableDAO {
    private final String GET_FREE = "from com.serviceSystem.entity.RestaurantTable r where r.freeStatus = true";

    public void save(RestaurantTable table) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(table);
        tx1.commit();
        session.close();
    }
    @Override
    public RestaurantTable getById(long id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        RestaurantTable table = session.get(RestaurantTable.class,id);
        transaction.commit();
        session.close();
        return table;
    }
    public List<RestaurantTable> getAll(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM com.serviceSystem.entity.RestaurantTable");
        List<RestaurantTable> tables = (List<RestaurantTable>) query.list();
        session.close();
        return tables;
    }

    @Override
    public void update(RestaurantTable table) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(table);
        transaction.commit();
        session.close();
    }

    public void updateFreeStatus(int id, boolean status){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        RestaurantTable table = session.get(RestaurantTable.class,id);
        table.setFreeStatus(status);
        session.update(table);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(RestaurantTable table) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(table);
        transaction.commit();
        session.close();
    }

    @Override
    public List<RestaurantTable> getFree(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery(GET_FREE);
        return (List<RestaurantTable>) query.list();
    }
}
