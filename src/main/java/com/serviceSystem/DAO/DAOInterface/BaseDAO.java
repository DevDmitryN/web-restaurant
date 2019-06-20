package com.serviceSystem.DAO.DAOInterface;

import java.util.List;

public interface BaseDAO<T> {
    void save(T entity);
//    void delete(T entity);
    void update(T entity);
    List<T> getAll();
    T getById(long id);
}