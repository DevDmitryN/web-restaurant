package com.serviceSystem.dao.DAOImpl;

import com.serviceSystem.dao.DAOInterface.DishInOrderDao;
import com.serviceSystem.entity.DishInOrder;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DishInOrderDaoImpl extends BaseDAOImpl<DishInOrder,Long> implements DishInOrderDao {
    public DishInOrderDaoImpl() {
        super(DishInOrder.class);
    }


    @Override
    @Transactional
    public void deleteAllByOrderId(long orderId) {
        List<DishInOrder> dishesInOrder = getByOrderId(orderId);
        for (DishInOrder dishInOrder : dishesInOrder) {
            getCurrentSession().delete(dishInOrder);
        }
    }

    @Override
    @Transactional
    public List<DishInOrder> getByOrderId(long orderId) {
        String hql = "from DishInOrder d where d.order.id = :orderId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("orderId",orderId);
        return (List<DishInOrder>) query.list();
    }
}
