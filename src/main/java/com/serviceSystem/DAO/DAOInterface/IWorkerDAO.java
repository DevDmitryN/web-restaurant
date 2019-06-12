package com.serviceSystem.DAO.DAOInterface;

import com.serviceSystem.entity.Worker;

public interface IWorkerDAO extends IBaseDAO<Worker> {
    void delete(Worker worker);
}
