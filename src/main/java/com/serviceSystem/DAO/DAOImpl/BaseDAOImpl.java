package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.BaseDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public abstract class BaseDAOImpl<T,ID extends Serializable> implements BaseDAO<T,ID> {



    private Class<T> classOfEntity;
    @Autowired
    private SessionFactory sessionFactory;


    public BaseDAOImpl(Class classOfEntity){
        this.classOfEntity = classOfEntity;
    }

    @Override
    @Transactional
    public ID save(T entity) {
        ID id = (ID) getCurrentSession().save(entity);
        return id;
    }

    @Override
    @Transactional
    public void update(T entity) {
        getCurrentSession().update(entity);
    }

    @Override
    @Transactional
    public List<T> getAll() {
        String GET_ALL = "from " + classOfEntity.getName();
        List<T> entities = (List<T>) getCurrentSession().createQuery(GET_ALL).list();
        return entities;
    }

    @Override
    @Transactional
    public T getById(ID id) {
        T entity = (T) getCurrentSession().get(classOfEntity.getName(),id);
        return entity;
    }

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
}
