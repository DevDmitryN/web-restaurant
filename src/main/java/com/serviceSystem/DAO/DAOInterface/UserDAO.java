package com.serviceSystem.DAO.DAOInterface;

import com.serviceSystem.entity.User;

public interface UserDAO<T extends User> extends BaseDAO<T> {
    T getByEmail(String email);
    boolean isExist(String email, String password);
}
