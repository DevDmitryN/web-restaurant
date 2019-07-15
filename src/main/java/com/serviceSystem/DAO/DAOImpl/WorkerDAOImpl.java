package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.WorkerDAO;
import com.serviceSystem.entity.Worker;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class WorkerDAOImpl extends UserDAOImpl<Worker,Integer> implements WorkerDAO {

    public WorkerDAOImpl(){
        super(Worker.class);
    }

    @Transactional
    public void delete(Worker worker){
        getCurrentSession().delete(worker);
    }

}
