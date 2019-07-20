package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.OrderDAO;
import com.serviceSystem.entity.Order;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderDAOImpl extends BaseDAOImpl<Order,Long> implements OrderDAO {
    private final String DELETE = "from com.serviceSystem.entity.Order o where o.id = :id";
    private final String GET_ACTIVE_BY_CLIENT_ID = "from com.serviceSystem.entity.Order o where o.client.id = :client_id";
    private final String GET_BY_TABLE_ID = "from com.serviceSystem.entity.Order o where o.table.id = :table_id";
    private final String GET_NOT_TAKEN_WITH_FREE_TABLE = "from com.serviceSystem.entity.Order o where o.table.freeStatus = true and status = 'NOT_TAKEN'";

    public OrderDAOImpl(){
        super(Order.class);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Query query = getCurrentSession().createQuery(DELETE);
        query.setParameter("id",id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public List<Order> getByTable(int tableId) {
        Query query = getCurrentSession().createQuery(GET_BY_TABLE_ID);
        query.setParameter("table_id",tableId);
        List<Order> orders = query.list();
        return orders;
    }

    @Override
    @Transactional
    public List<Order> getActiveByClientId(long clientId) {
        Query query = getCurrentSession().createQuery(GET_ACTIVE_BY_CLIENT_ID);
        query.setParameter("client_id",clientId);
        List<Order> orders = (List<Order>) query.list();
        return orders;
    }

    @Override
    @Transactional
    public List<Order> getNotTakenWithFreeTable() {
        Query query = getCurrentSession().createQuery(GET_NOT_TAKEN_WITH_FREE_TABLE);
        List<Order> orders = query.list();
        return orders;
    }
}
