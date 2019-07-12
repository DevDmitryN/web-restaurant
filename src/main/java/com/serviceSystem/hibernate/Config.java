package com.serviceSystem.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
//@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.serviceSystem.DAO","com.serviceSystem.service"})
@ImportResource({ "classpath:hibernate.cfg.xml" })
public class Config {


}
