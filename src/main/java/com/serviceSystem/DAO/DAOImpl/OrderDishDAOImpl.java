package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.OrderDishDAO;
import com.serviceSystem.entity.OrderDish;

public class OrderDishDAOImpl extends BaseDAOImpl<OrderDish,Long> implements OrderDishDAO {
    public OrderDishDAOImpl() {
        super(OrderDish.class);
    }

}
