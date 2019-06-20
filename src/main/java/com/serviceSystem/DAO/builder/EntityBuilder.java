package com.serviceSystem.DAO.builder;

import com.serviceSystem.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.serviceSystem.DAO.builder.ColumnNames.*;

public class EntityBuilder {

    public static Order buildOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong(ORDER_ID));
        order.setStatus(resultSet.getString(ORDER_STATUS));
        order.setTable(buildTable(resultSet));
        order.setClient(buildClient(resultSet));
        order.setWorker(buildWorker(resultSet));
        order.setCreationTime(resultSet.getTimestamp(CREATION_TIME).toLocalDateTime());
        order.setBookingTime(resultSet.getTimestamp(BOOKING_TIME).toLocalDateTime());
        return order;
    }
    public static Client buildClient(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getLong(CLIENT_ID));
        client.setName(resultSet.getString(CLIENT_NAME));
        client.setSurname(resultSet.getString(CLIENT_SURNAME));
        client.setEmail(resultSet.getString(CLIENT_EMAIL));
        client.setPhoneNumber(resultSet.getString(CLIENT_PHONE));
        client.setCardNumber(resultSet.getString(CLIENT_CARD));
        return client;
    }
    public static Worker buildWorker(ResultSet resultSet)throws SQLException{
        Worker worker = new Worker();
        if(resultSet.getString(WORKER_NAME)==null){
            return worker;
        }
        worker.setId(resultSet.getLong(WORKER_ID));
        worker.setName(resultSet.getString(WORKER_NAME));
        worker.setSurname(resultSet.getString(WORKER_SURNAME));
        worker.setEmail(resultSet.getString(WORKER_EMAIL));
        worker.setPhoneNumber(resultSet.getString(WORKER_PHONE));
        worker.setRole(resultSet.getString(WORKER_ROLE));
        return worker;
    }
    public static RestaurantTable buildTable(ResultSet resultSet)throws SQLException{
        RestaurantTable table = new RestaurantTable();
        table.setId(resultSet.getInt(TABLE_ID));
        table.setCapacity(resultSet.getInt(TABLE_CAPACITY));
        table.setFreeStatus(resultSet.getBoolean(TABLE_STATUS));
        return table;
    }
    public static Dish buildDish(ResultSet resultSet)throws SQLException{
        Dish dish = new Dish();
        dish.setId(resultSet.getInt(DISH_ID));
        dish.setName(resultSet.getString(DISH_NAME));
        dish.setPrice(resultSet.getDouble(DISH_PRICE));
        dish.setDescription(resultSet.getString(DISH_DESCRIPTION));
        return dish;
    }
    public static List<Dish> buildDishes(ResultSet resultSet)throws SQLException{
        List<Dish> dishes = new ArrayList<Dish>();
        while (resultSet.next()) {
            dishes.add(buildDish(resultSet));
        }
        return dishes;
    }
}
