package com.serviceSystem.dao.DAOImpl;

import com.serviceSystem.dao.DAOInterface.OrderDishDAO;
import com.serviceSystem.entity.OrderDish;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDishDAOImpl extends BaseDAOImpl<OrderDish,Long> implements OrderDishDAO {
    public OrderDishDAOImpl() {
        super(OrderDish.class);
    }

}
