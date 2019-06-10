package com.serviceSystem.dao;

import com.serviceSystem.entity.Dish;
import com.serviceSystem.hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DishDao{
    public void insert(Dish dish) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(dish);
        tx1.commit();
        session.close();
    }

    public List<Dish> getDishes(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Dish");
        List<Dish> dishes = (List<Dish>) query.list();
        transaction.commit();
        session.close();
        return dishes;
    }
    public Dish getDishById(int id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Dish dish = session.get(Dish.class,id);
        transaction.commit();
        session.close();
        return dish;
    }
}
