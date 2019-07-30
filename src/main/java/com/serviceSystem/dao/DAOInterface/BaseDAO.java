package com.serviceSystem.dao.DAOInterface;

import java.util.List;

public interface BaseDAO<T, ID> {
    ID save(T entity);
    void update(T entity);
    List<T> getAll();
    T getById(ID id);
}
