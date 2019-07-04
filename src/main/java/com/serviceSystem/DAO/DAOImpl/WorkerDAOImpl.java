package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.WorkerDAO;
import com.serviceSystem.entity.Worker;
import com.serviceSystem.hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class WorkerDAOImpl implements WorkerDAO {
    private final String IS_EXIST = "from com.serviceSystem.entity.Worker w where w.email like :email and w.password like :password";
    private final String GET_BY_EMAIL = "from com.serviceSystem.entity.Worker w where w.email like :email";
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

    @Override
    public boolean isExist(String email, String password) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery(IS_EXIST);
        query.setParameter("email",email);
        query.setParameter("password",password);
        List<Worker> worker = query.list();
        return worker.size() == 0 ? false : true;
    }

    @Override
    public Worker getByEmail(String email) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery(GET_BY_EMAIL);
        query.setParameter("email",email);
        return  (Worker) query.list().get(0);
    }
}
