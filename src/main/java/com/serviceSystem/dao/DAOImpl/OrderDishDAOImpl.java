package com.serviceSystem.dao.DAOImpl;

import com.serviceSystem.dao.DAOInterface.OrderDishDAO;
import com.serviceSystem.entity.DishInOrder;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDishDAOImpl extends BaseDAOImpl<DishInOrder,Long> implements OrderDishDAO {
    public OrderDishDAOImpl() {
        super(DishInOrder.class);
    }

}
