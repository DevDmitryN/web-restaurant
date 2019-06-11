package com.serviceSystem.dao;

import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.Order;

import java.sql.*;
import java.util.Properties;

public class OrderDaoJDBC {
    Properties properties;
    String url;
    String username;
    String password;
    public OrderDaoJDBC(Properties properties){
        this.properties = properties;
        url = properties.getProperty("url");
        username = properties.getProperty("username");;
        password = properties.getProperty("password");
    }

    public OrderDaoJDBC(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void insert(Order order){
        try(Connection connection = DriverManager.getConnection(url,username,password);
                Statement statement = connection.createStatement();){
            String sql = "INSERT INTO restaurantdb.orders (totalPrice,status,table_id)" +
                    "VAlUES (" + order.getTotalPrice() + ",'NOT_TAKEN'," + order.getTable().getId() + ")";
            statement.executeUpdate(sql);
            sql = "SELECT id FROM restaurantdb.orders ORDER BY id DESC LIMIT 1";
            ResultSet set = statement.executeQuery(sql);
            int id = set.next() ? set.getInt(1) : 0;
            for(Dish d : order.getDishes()){
                sql = "INSERT INTO restaurantdb.order_dish (order_id, dish_id) VALUES (" +
                        id + "," + d.getId() + ")";
                statement.executeUpdate(sql);
            }
            sql = "update restaurantdb.tables set is_free=false where id=" + order.getTable().getId();
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
