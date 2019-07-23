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
        String getByEmail = "from " + classOfUser.getName() + " user where user.email = :email";
        Query query = getCurrentSession().createQuery(getByEmail);
        query.setParameter("email",email);
        return (T) query.getSingleResult();
    }

    @Override
    @Transactional
    public boolean isExist(String email, String password) {
        String getByEmailAndPassword = "from " + classOfUser.getName() + " user where user.email = :email and user.password = :password";
        Query query = getCurrentSession().createQuery(getByEmailAndPassword);
        query.setParameter("email",email);
        query.setParameter("password",password);
        List<T> users = query.list();
        return users.size() == 0 ? false : true;
    }

    @Override
    @Transactional
    public boolean isEmailExist(String email) {
        String getByEmail = "from " + classOfUser.getName() + " user where user.email = :email";
        Query query = getCurrentSession().createQuery(getByEmail);
        query.setParameter("email",email);
        return query.list().size() != 0;
    }

    @Override
    @Transactional
    public boolean isPhoneNumberExist(String phoneNumber) {
        String getByPhoneNumber = "from " + classOfUser.getName() + " u where u.phoneNumber = :phoneNumber";
        Query query = getCurrentSession().createQuery(getByPhoneNumber);
        query.setParameter("phoneNumber",phoneNumber);
        return query.list().size() != 0;
    }
}
