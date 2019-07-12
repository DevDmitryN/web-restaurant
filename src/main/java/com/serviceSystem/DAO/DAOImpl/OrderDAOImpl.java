package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.OrderDAO;
import com.serviceSystem.entity.Order;
import com.serviceSystem.hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl extends BaseDAOImpl<Order,Long> implements OrderDAO {
    private final String DELETE = "from com.serviceSystem.entity.Order o where o.id = :id";
    private final String GET_ACTIVE_BY_CLIENT_ID = "from com.serviceSystem.entity.Order o where o.client.id = :client_id";
    private final String GET_BY_TABLE_ID = "from com.serviceSystem.entity.Order o where o.table.id = :table_id";
    private final String GET_NOT_TAKEN_WITH_FREE_TABLE = "from com.serviceSystem.entity.Order o where o.table.freeStatus = true and status like 'NOT_TAKEN'";

    public OrderDAOImpl(){
        super(Order.class);
    }

    @Override
    public void delete(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(DELETE);
        query.setParameter("id",id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public List<Order> getByTable(int tableId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery(GET_BY_TABLE_ID);
        query.setParameter("table_id",tableId);
        List<Order> orders = query.list();
        return orders;
    }

    @Override
    public List<Order> getActiveByClientId(long clientId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery(GET_ACTIVE_BY_CLIENT_ID);
        query.setParameter("client_id",clientId);
        List<Order> orders = (List<Order>) query.list();
        session.close();
        return orders;
    }

    @Override
    public List<Order> getNotTakenWithFreeTable() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery(GET_NOT_TAKEN_WITH_FREE_TABLE);
        List<Order> orders = query.list();
        session.close();
        return orders;
    }
}
