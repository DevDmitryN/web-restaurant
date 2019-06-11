package com.serviceSystem.dao;

import com.serviceSystem.entity.Worker;
import com.serviceSystem.hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class WorkerDao {
    public void add(Worker worker){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(worker);
        transaction.commit();
        session.close();
    }
    public Worker getWorkerById(long id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Worker worker = session.get(Worker.class,id);
        transaction.commit();
        session.close();
        return worker;
    }
}
