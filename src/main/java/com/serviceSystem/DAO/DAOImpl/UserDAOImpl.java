package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.UserDAO;
import com.serviceSystem.entity.User;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class UserDAOImpl<T extends User,ID extends Number> extends BaseDAOImpl<T,ID> implements UserDAO<T,ID> {

    private final Class<T> classOfUser;

    public UserDAOImpl(Class classOfUser){
        super(classOfUser);
        this.classOfUser = classOfUser;
    }
    @Override
    @Transactional
    public T getByEmail(String email) {
        String getByEmail = "from " + classOfUser.getName() + " user where user.email like :email";
        Query query = getCurrentSession().createQuery(getByEmail);
        query.setParameter("email",email);
        List objects = query.list();
//        if(objects.size() != 0){
//            T user = (T) query.list().get(0);
//            return user;
//        }else{
//            return null;
//        }
        return (T) query.list().get(0);
    }

    @Override
    @Transactional
    public boolean isExist(String email, String password) {
        String getByEmailAndPassword = "from " + classOfUser.getName() + " user where user.email like :email and user.password like :password";
        Query query = getCurrentSession().createQuery(getByEmailAndPassword);
        query.setParameter("email",email);
        query.setParameter("password",password);
        List<T> users = query.list();
        return users.size() == 0 ? false : true;
    }

    @Override
    @Transactional
    public boolean isEmailExist(String email) {
        String getByEmail = "from " + classOfUser.getName() + " user where user.email like :email";
        Query query = getCurrentSession().createQuery(getByEmail);
        query.setParameter("email",email);
        boolean result = query.list().size() == 0 ? false : true;
        return result;
    }


}
