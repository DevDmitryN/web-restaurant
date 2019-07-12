package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.UserDAO;
import com.serviceSystem.entity.User;
import com.serviceSystem.entity.Worker;
import com.serviceSystem.hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public abstract class UserDAOImpl<T extends User,ID extends Number> extends BaseDAOImpl<T,ID> implements UserDAO<T,ID> {

    private final Class<T> classOfUser;

    public UserDAOImpl(Class classOfUser){
        super(classOfUser);
        this.classOfUser = classOfUser;
    }
    @Override
    public T getByEmail(String email) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String getByEmail = "from " + classOfUser.getName() + " user where user.email like :email";
        Query query = session.createQuery(getByEmail);
        query.setParameter("email",email);
        T user = (T) query.list().get(0);
        session.close();
        return  user;
    }

    @Override
    public boolean isExist(String email, String password) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String getByEmailAndPassword = "from " + classOfUser.getName() + " user where user.email like :email and user.password like :password";
        Query query = session.createQuery(getByEmailAndPassword);
        query.setParameter("email",email);
        query.setParameter("password",password);
        List<T> users = query.list();
        session.close();
        return users.size() == 0 ? false : true;
    }

    @Override
    public boolean isEmailExist(String email) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String getByEmail = "from " + classOfUser.getName() + " user where user.email like :email";
        Query query = session.createQuery(getByEmail);
        query.setParameter("email",email);
        boolean result = query.list().size() == 0 ? false : true;
        session.close();
        return result;
    }


}
