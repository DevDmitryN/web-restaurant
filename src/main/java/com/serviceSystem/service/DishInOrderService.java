package com.serviceSystem.service;

import com.serviceSystem.dao.DAOInterface.DishInOrderDao;
import com.serviceSystem.entity.DishInOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishInOrderService {

    @Autowired
    private DishInOrderDao dishInOrderDao;


    public void save(DishInOrder dishInOrder){
        dishInOrderDao.save(dishInOrder);
    }

    public void deleteAllByOrderId(long orderId){
        dishInOrderDao.deleteAllByOrderId(orderId);
    }

    public List<DishInOrder> getByOrderId(long orderId){
        return dishInOrderDao.getByOrderId(orderId);
    }

}
