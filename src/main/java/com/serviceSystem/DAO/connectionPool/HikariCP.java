package com.serviceSystem.DAO.connectionPool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HikariCP {

    private static final ResourceBundle DB_CONFIG = ResourceBundle.getBundle("dbconfig");
    private static final String URL = DB_CONFIG.getString("url");
    private static final String USERNAME = DB_CONFIG.getString("username");
    private static final String PASSWORD = DB_CONFIG.getString("password");
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;

    static {
        config.setJdbcUrl(URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);

//        config.addDataSourceProperty("cachePrepStmts", "true");
//        config.addDataSourceProperty("prepStmtCacheSize", "250");
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private HikariCP(){}
}
