package com.serviceSystem.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;
import static org.hibernate.cfg.AvailableSettings.C3P0_MAX_STATEMENTS;

@Configuration
@PropertySource("classpath:application.properties")
@PropertySource("classpath:messages.properties")
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.serviceSystem.dao","com.serviceSystem.service"})
public class ApplicationConfig {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        Properties properties = new Properties();

        factoryBean.setDataSource(dataSource());

        properties.put(SHOW_SQL, environment.getProperty("hibernate.show_sql"));
        properties.put(FORMAT_SQL, environment.getProperty("hibernate.format_sql"));
        properties.put(USE_SQL_COMMENTS, environment.getProperty("hibernate.use_sql_comments"));
        //properties.put(CURRENT_SESSION_CONTEXT_CLASS,environment.getProperty("hibernate.current_session_context_class"));
        //todo schema validation fix
        //properties.put(HBM2DDL_AUTO, environment.getProperty("hibernate.hbm2ddl.auto"));

        properties.put(C3P0_MIN_SIZE, environment.getProperty("hibernate.c3p0.min_size"));
        properties.put(C3P0_MAX_SIZE, environment.getProperty("hibernate.c3p0.max_size"));
        properties.put(C3P0_ACQUIRE_INCREMENT, environment.getProperty("hibernate.c3p0.acquire_increment"));
        properties.put(C3P0_TIMEOUT, environment.getProperty("hibernate.c3p0.timeout"));
        properties.put(C3P0_MAX_STATEMENTS, environment.getProperty("hibernate.c3p0.max_statements"));

        factoryBean.setHibernateProperties(properties);
        factoryBean.setPackagesToScan("com.serviceSystem.entity");
        System.out.println(factoryBean);
        return factoryBean;
    }
    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        System.out.println(sessionFactory().getObject());
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("postgresql.driver"));
        dataSource.setUrl(environment.getProperty("postgresql.jdbcUrl"));
        dataSource.setUsername(environment.getProperty("postgresql.username"));
        dataSource.setPassword(environment.getProperty("postgresql.password"));
        return dataSource;
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
