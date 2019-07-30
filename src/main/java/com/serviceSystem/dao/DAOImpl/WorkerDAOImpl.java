package com.serviceSystem.dao.DAOImpl;

import com.serviceSystem.dao.DAOInterface.WorkerDAO;
import com.serviceSystem.entity.Worker;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
