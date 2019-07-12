package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.WorkerDAO;
import com.serviceSystem.entity.Worker;
import com.serviceSystem.hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkerDAOImpl extends UserDAOImpl<Worker,Integer> implements WorkerDAO {

    public WorkerDAOImpl(){
        super(Worker.class);
    }
    public void delete(Worker worker){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(worker);
        transaction.commit();
        session.close();
    }

}
