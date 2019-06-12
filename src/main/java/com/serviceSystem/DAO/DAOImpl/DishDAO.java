package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.IBaseDAO;
import com.serviceSystem.DAO.DAOInterface.IDishDAO;
import com.serviceSystem.entity.Dish;
import com.serviceSystem.hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DishDAO implements IDishDAO {
    @Override
    public void save(Dish dish) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(dish);
        tx1.commit();
        session.close();
    }
    @Override
    public List<Dish> getAll(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM com.serviceSystem.entity.Dish");
        List<Dish> dishes = (List<Dish>) query.list();
        transaction.commit();
        session.close();
        return dishes;
    }
    @Override
    public Dish getById(long id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Dish dish = session.get(Dish.class,id);
        transaction.commit();
        session.close();
        return dish;
    }
    @Override
    public void update(Dish updatedDish){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(updatedDish);
        transaction.commit();
        session.close();
    }
    @Override
    public void delete(Dish dish){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(dish);
        transaction.commit();
        session.close();
    }


}
