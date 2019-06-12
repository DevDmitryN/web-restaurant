package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.IWorkerDAO;
import com.serviceSystem.entity.Worker;
import com.serviceSystem.hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class WorkerDAO implements IWorkerDAO {
    @Override
    public void save(Worker worker){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(worker);
        transaction.commit();
        session.close();
    }
    @Override
    public Worker getById(long id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Worker worker = session.get(Worker.class,id);
        transaction.commit();
        session.close();
        return worker;
    }

    @Override
    public void delete(Worker worker) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(worker);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Worker worker) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(worker);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Worker> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM com.serviceSystem.entity.Worker");
        List<Worker> workers = query.list();
        session.close();
        return workers;
    }
}
