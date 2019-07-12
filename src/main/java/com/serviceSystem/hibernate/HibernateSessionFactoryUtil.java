package com.serviceSystem.hibernate;


import com.serviceSystem.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}
    private static final Logger logger = LoggerFactory.getLogger(HibernateSessionFactoryUtil.class);
    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                configuration.addAnnotatedClass(Dish.class);
                configuration.addAnnotatedClass(RestaurantTable.class);
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(Worker.class);
                configuration.addAnnotatedClass(OrderDish.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                logger.warn(e.getMessage());
            }
        }
        return sessionFactory;
    }
}
