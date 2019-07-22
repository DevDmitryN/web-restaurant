package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.DishDAO;
import com.serviceSystem.entity.Dish;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import java.util.List;

@Repository
public class DishDAOImpl extends BaseDAOImpl<Dish,Integer> implements DishDAO {

    public DishDAOImpl(){
        super(Dish.class);
    }


    @Override
    @Transactional
    public List<Dish> getWhichAreInMenu() {
        String hql = "from com.serviceSystem.entity.Dish d where d.isInMenu = true";
        return (List<Dish>) getCurrentSession().createQuery(hql).list();
    }
}
