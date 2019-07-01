package com.serviceSystem.DAO.DAOInterface;

import com.serviceSystem.entity.Worker;

public interface WorkerDAO extends BaseDAO<Worker> {
    void delete(Worker worker);
    boolean isExist(String phoneNumber, String password);
}
