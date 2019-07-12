package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.RestaurantTableDAO;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TableDAOImpl extends BaseDAOImpl<RestaurantTable,Integer> implements RestaurantTableDAO {
    private final String GET_FREE = "from com.serviceSystem.entity.RestaurantTable r where r.freeStatus = true";

    public TableDAOImpl(){
        super(RestaurantTable.class);
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
        List<RestaurantTable> tables = (List<RestaurantTable>) query.list();
        session.close();
        return tables;
    }
}
