package com.serviceSystem.dao;

import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RestaurantTableDao {

    public void insert(RestaurantTable table) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(table);
        tx1.commit();
        session.close();
    }
    public RestaurantTable getRestaurantTableById(int id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        RestaurantTable table = session.get(RestaurantTable.class,id);
        transaction.commit();
        session.close();
        return table;
    }
    public List<RestaurantTable> getRestaurantTables(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM RestaurantTable");
        List<RestaurantTable> tables = (List<RestaurantTable>) query.list();
        transaction.commit();
        session.close();
        return tables;
    }
}
