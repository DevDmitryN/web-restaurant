package com.serviceSystem.dao.DAOImpl;

import com.serviceSystem.dao.DAOInterface.UserDAO;
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
        String getByEmail = "from " + classOfUser.getName() + " user where user.email = :email";
        Query query = getCurrentSession().createQuery(getByEmail);
        query.setParameter("email",email);
        return (T) query.uniqueResult();
    }

//    @Override
//    @Transactional
//    public boolean isExist(String email, String password) {
//        String getByEmailAndPassword = "from " + classOfUser.getName() + " user where user.email = :email and user.password = :password";
//        Query query = getCurrentSession().createQuery(getByEmailAndPassword);
//        query.setParameter("email",email);
//        query.setParameter("password",password);
//        List<T> users = query.list();
//        return users.size() == 0 ? false : true;
//    }

    @Override
    @Transactional
    public boolean isEmailExist(String email) {
        return getByEmail(email) != null;
    }

    @Override
    @Transactional
    public T getByPhoneNumber(String phoneNumber) {
        String hql = "from " + classOfUser.getName() + " u where u.phoneNumber = :phoneNumber";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("phoneNumber",phoneNumber);
        return (T) query.uniqueResult();
    }

    @Override
    @Transactional
    public boolean isPhoneNumberExist(String phoneNumber) {
        return getByPhoneNumber(phoneNumber) != null;
    }
}
