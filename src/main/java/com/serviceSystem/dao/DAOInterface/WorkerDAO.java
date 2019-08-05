package com.serviceSystem.dao.DAOInterface;

import com.serviceSystem.entity.Worker;

import java.util.List;

public interface WorkerDAO extends UserDAO<Worker,Integer> {
    void delete(Worker worker);
    List<Worker> getStaff();
}
