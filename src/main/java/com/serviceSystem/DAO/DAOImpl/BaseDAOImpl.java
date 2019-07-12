package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.BaseDAO;
import com.serviceSystem.hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public abstract class BaseDAOImpl<T,ID extends Serializable> implements BaseDAO<T,ID> {



    private Class<T> classOfEntity;
    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();


    public BaseDAOImpl(Class classOfEntity){
        this.classOfEntity = classOfEntity;
    }

    @Override
    public ID save(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ID id = (ID) session.save(entity);
        transaction.commit();
        session.close();
        System.out.println(id);
        return id;
    }

    @Override
    public void update(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public List<T> getAll() {
        String GET_ALL = "from " + classOfEntity.getName();
        Session session = sessionFactory.openSession();
        List<T> entities = (List<T>) session.createQuery(GET_ALL).list();
        session.close();
        return entities;
    }

    @Override
    public T getById(ID id) {
        Session session = sessionFactory.openSession();
        T entity = (T) session.get(classOfEntity.getName(),id);
        session.close();
        return entity;
    }

    protected SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
