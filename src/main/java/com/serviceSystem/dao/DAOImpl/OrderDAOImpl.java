package com.serviceSystem.dao.DAOImpl;

import com.serviceSystem.dao.DAOInterface.OrderDAO;
import com.serviceSystem.entity.Order;
import com.serviceSystem.exception.NoSuchItemException;
import org.hibernate.Hibernate;
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
    public List<Order> getActiveOrNotByClientId(long clientId, boolean isActive) {
        String GET_ACTIVE_BY_CLIENT_ID = "from com.serviceSystem.entity.Order o where o.isActive = :isActive and o.client.id = :client_id order by o.id desc ";
        Query query = getCurrentSession().createQuery(GET_ACTIVE_BY_CLIENT_ID);
        query.setParameter("isActive",isActive);
        query.setParameter("client_id",clientId);
        List<Order> orders = (List<Order>) query.list();
        for (Order order : orders) {
            Hibernate.initialize(order.getDishesInOrder());
        }
        return orders;
    }


//    @Override
//    @Transactional
//    public List<Order> getNotTakenWithFreeTable() {
//        String GET_NOT_TAKEN_WITH_FREE_TABLE = "from com.serviceSystem.entity.Order o where o.table.freeStatus = true and status = 'NOT_TAKEN' order by o.id desc ";
//        Query query = getCurrentSession().createQuery(GET_NOT_TAKEN_WITH_FREE_TABLE);
//        List<Order> orders = query.list();
//        return orders;
//    }

    @Override
    @Transactional
    public List<Order> getActiveForTable(int tableId) {
        String hql = "from com.serviceSystem.entity.Order o where o.isActive = true and o.table.id = :tableId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("tableId",tableId);
        return (List<Order>) query.list();
    }

    @Override
    @Transactional
    public boolean isBookingPeriodValidForTable(LocalDateTime begin, LocalDateTime end,int tableId) {
        String hql = "from com.serviceSystem.entity.Order o where o.isActive = true and ( :begin between o.bookingTimeBegin and o.bookingTimeEnd" +
                " or :end between o.bookingTimeBegin and o.bookingTimeEnd ) and o.table.id = :tableId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("begin",begin);
        query.setParameter("end",end);
        query.setParameter("tableId",tableId);
        return query.list().size() == 0;
    }

    @Override
    @Transactional
    public Order getActiveById(long id) {
        String hql = "from com.serviceSystem.entity.Order o where o.isActive = true and o.id = :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        return (Order) query.uniqueResult();
    }


//    @Override
//    @Transactional
//    public List<Order> getNotTakenForTable(int tableId) {
//        Query query = getCurrentSession().createQuery("from com.serviceSystem.entity.Order o where o.status = 'NOT_TAKEN' and o.table.id = :tableId order by o.bookingTime desc");
//        query.setParameter("tableId",tableId);
//        return (List<Order>) query.list();
//    }
}
