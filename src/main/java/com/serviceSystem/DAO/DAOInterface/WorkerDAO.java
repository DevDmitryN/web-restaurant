package com.serviceSystem.DAO.DAOInterface;

import com.serviceSystem.entity.Worker;

public interface WorkerDAO extends UserDAO<Worker> {
    void delete(Worker worker);
}
