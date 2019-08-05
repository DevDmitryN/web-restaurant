package com.serviceSystem.dao.DAOImpl;

import com.serviceSystem.dao.DAOInterface.WorkerDAO;
import com.serviceSystem.entity.Worker;
import org.hibernate.query.Query;
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

    @Override
    @Transactional
    public List<Worker> getStaff() {
        String hql = "from Worker w where w.isInStaff = true";
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }

}
