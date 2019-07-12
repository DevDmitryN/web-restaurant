package com.serviceSystem.DAO.DAOInterface;

import com.serviceSystem.entity.User;

public interface UserDAO<T extends User,ID extends Number> extends BaseDAO<T,ID> {
    T getByEmail(String email);
    boolean isExist(String email, String password);
    boolean isEmailExist(String email);
}
