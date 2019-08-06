package com.serviceSystem.dao.DAOImpl;

import com.serviceSystem.dao.DAOInterface.OrderDAO;
import com.serviceSystem.entity.Order;
import com.serviceSystem.exception.NoSuchItemException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderDAOImpl extends BaseDAOImpl<Order,Long> implements OrderDAO {
    public OrderDAOImpl(){
        super(Order.class);
    }

    @Override
    @Transactional
    public void delete(Order order) {
        getCurrentSession().delete(order);
    }

    @Override
    @Transactional
    public List<Order> getByTable(int tableId) {
        String GET_BY_TABLE_ID = "from com.serviceSystem.entity.Order o where o.table.id = :table_id order by o.id desc ";
        Query query = getCurrentSession().createQuery(GET_BY_TABLE_ID);
        query.setParameter("table_id",tableId);
        List<Order> orders = query.list();
        return orders;
    }

    @Override
    @Transactional
    public List<Order> getActiveByClientId(long clientId) {
        String GET_ACTIVE_BY_CLIENT_ID = "from com.serviceSystem.entity.Order o where o.client.id = :client_id order by o.id desc ";
        Query query = getCurrentSession().createQuery(GET_ACTIVE_BY_CLIENT_ID);
        query.setParameter("client_id",clientId);
        List<Order> orders = (List<Order>) query.list();
        return orders;
    }

    @Override
    @Transactional
    public List<Order> getNotTakenWithFreeTable() {
        String GET_NOT_TAKEN_WITH_FREE_TABLE = "from com.serviceSystem.entity.Order o where o.table.freeStatus = true and status = 'NOT_TAKEN' order by o.id desc ";
        Query query = getCurrentSession().createQuery(GET_NOT_TAKEN_WITH_FREE_TABLE);
        List<Order> orders = query.list();
        return orders;
    }

    @Override
    @Transactional
    public List<Order> getActiveForTable(int tableId) {
        LocalDateTime now = LocalDateTime.now();
        String hql = "from com.serviceSystem.entity.Order o where o.bookingTimeEnd >= :now and o.table.id = :tableId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("now",now);
        query.setParameter("tableId",tableId);
        return (List<Order>) query.list();
    }

//    @Override
//    @Transactional
//    public List<Order> getNotTakenForTable(int tableId) {
//        Query query = getCurrentSession().createQuery("from com.serviceSystem.entity.Order o where o.status = 'NOT_TAKEN' and o.table.id = :tableId order by o.bookingTime desc");
//        query.setParameter("tableId",tableId);
//        return (List<Order>) query.list();
//    }
}
