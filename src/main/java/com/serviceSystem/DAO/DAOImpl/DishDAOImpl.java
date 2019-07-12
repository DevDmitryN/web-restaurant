package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.DishDAO;
import com.serviceSystem.entity.Dish;
import com.serviceSystem.hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DishDAOImpl extends BaseDAOImpl<Dish,Integer> implements DishDAO {

    public DishDAOImpl(){
        super(Dish.class);
    }

//    @Override
//    public void delete(Dish dish){
//        Session session = getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.delete(dish);
//        transaction.commit();
//        session.close();
//    }
//    @Override
//    public void delete(Integer id){
//        Session session = getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        transaction.commit();
//        session.close();
//    }

}
