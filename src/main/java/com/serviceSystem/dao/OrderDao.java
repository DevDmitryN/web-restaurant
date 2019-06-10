package com.serviceSystem.dao;

import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.Order;

import java.sql.*;
import java.util.Properties;

public class OrderDao {
    Properties properties;
    public OrderDao(Properties properties){
        this.properties = properties;
    }
    public void insert(Order order){
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
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
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
