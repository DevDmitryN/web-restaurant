package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.RestaurantTableDAO;
import com.serviceSystem.entity.RestaurantTable;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TableDAOImpl extends BaseDAOImpl<RestaurantTable,Integer> implements RestaurantTableDAO {
    private final String GET_FREE = "from com.serviceSystem.entity.RestaurantTable r where r.freeStatus = true";

    public TableDAOImpl(){
        super(RestaurantTable.class);
    }

    @Override
    @Transactional
    public void updateFreeStatus(int id, boolean status){
        RestaurantTable table = getCurrentSession().get(RestaurantTable.class,id);
        table.setFreeStatus(status);
        getCurrentSession().update(table);
    }

    @Override
    @Transactional
    public void delete(RestaurantTable table) {
        getCurrentSession().delete(table);
    }

    @Override
    public List<RestaurantTable> getFree(){
        Query query = getCurrentSession().createQuery(GET_FREE);
        List<RestaurantTable> tables = (List<RestaurantTable>) query.list();
        return tables;
    }
}
