package com.serviceSystem.DAO.DAOInterface;

import com.serviceSystem.entity.Worker;

public interface WorkerDAO extends UserDAO<Worker,Integer> {
    void delete(Worker worker);
}
