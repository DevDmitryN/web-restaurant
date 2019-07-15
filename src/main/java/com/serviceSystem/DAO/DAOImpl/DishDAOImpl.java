package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.DishDAO;
import com.serviceSystem.entity.Dish;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
