package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.OrderDAO;
import com.serviceSystem.DAO.builder.EntityBuilder;
import com.serviceSystem.DAO.connectionPool.HikariCP;
import com.serviceSystem.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class OrderDaoJDBCImpl implements OrderDAO {
    private final String SELECT_ALL = "select o.id,o.status," +
            "t.id as t_id,t.capacity as t_capacity,t.is_free as t_is_free," +
            "c.id as c_id,c.name as c_name,c.surname as c_surname,c.email as c_email,c.phone_number as c_phone_number,c.card_number as c_card_number," +
            "w.id as w_id,w.name as w_name,w.surname as w_surname,w.email as w_email,w.phone_number as w_phone_number,w.role as w_role " +
            "from restaurantdb.orders as o " +
            "join restaurantdb.\"tables\" as t on o.table_id = t.id " +
            "join restaurantdb.clients as c on o.client_id = c.id " +
            "join restaurantdb.workers as w on o.worker_id = w.id";
    private final String DISHES_FROM_ORDER = "select d.id,name,price,description from restaurantdb.order_dish as od\n" +
            "join restaurantdb.dishes as d on od.dish_id=d.id\n" +
            "where od.order_id=?";
    private final String ORDER_BY_ID = SELECT_ALL + " where o.id=?";
    private final String INSERT_INTO_ORDERS = "INSERT INTO restaurantdb.orders (table_id,client_id,worker_id) VALUES (?,?,?) RETURNING id";
    private final String INSERT_INTO_ORDER_DISH = "INSERT INTO restaurantdb.order_dish (order_id, dish_id) VALUES (?,?)";
    private final String DELETE_FROM_ORDER_DISH = "delete from restaurantdb.order_dish where order_id=?";
    private final String DELETE_FROM_ORDERS = "delete from restaurantdb.orders where id=?";
    private final String ORDERS_BY_TABLEID = SELECT_ALL + " where o.table_id=?";

    @Override
    public void save(Order order) {
        try (Connection connection = HikariCP.getConnection();) {
            try {
                connection.setAutoCommit(false);
                PreparedStatement statement = connection.prepareStatement(INSERT_INTO_ORDERS);
                statement.setInt(1, order.getTable().getId());
                statement.setLong(2, order.getClient().getId());
                statement.setLong(3, order.getWorker().getId());
                ResultSet resultSet = statement.executeQuery();
                long id = resultSet.next() ? resultSet.getLong(1) : -1;
                statement = connection.prepareStatement(INSERT_INTO_ORDER_DISH);
                for (Dish dish : order.getDishes()) {
                    statement.setLong(1, id);
                    statement.setInt(2, dish.getId());
                    statement.executeUpdate();
                }
                connection.commit();
            }catch (SQLException e){
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Order order) {
        try (Connection connection = HikariCP.getConnection()) {
            try{
                connection.setAutoCommit(false);

                connection.commit();
            }catch (SQLException e){
                connection.rollback();
                throw e;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<Order>();
        try (Connection connection = HikariCP.getConnection()) {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet resultSetOrders = statement.executeQuery(SELECT_ALL);
            ResultSet resultSetDishes = null;
            while (resultSetOrders.next()) {
                PreparedStatement preparedStatement = connection.prepareStatement(DISHES_FROM_ORDER);
                preparedStatement.setInt(1, resultSetOrders.getInt(1));
                resultSetDishes = preparedStatement.executeQuery();
                Order order = EntityBuilder.buildOrder(resultSetOrders);
                order.setDishes(EntityBuilder.buildDishes(resultSetDishes));
                orders.add(order);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order getById(long id) {
        Order order = null;
        try (Connection connection = HikariCP.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ORDER_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = EntityBuilder.buildOrder(resultSet);
                preparedStatement = connection.prepareStatement(DISHES_FROM_ORDER);
                preparedStatement.setLong(1, id);
                resultSet = preparedStatement.executeQuery();
                order.setDishes(EntityBuilder.buildDishes(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void delete(long id){
        try(Connection connection = HikariCP.getConnection()){
            try {
                connection.setAutoCommit(false);
                PreparedStatement statement = connection.prepareStatement(DELETE_FROM_ORDER_DISH);
                statement.setLong(1, id);
                statement.executeUpdate();
                statement = connection.prepareStatement(DELETE_FROM_ORDERS);
                statement.setLong(1, id);
                statement.executeUpdate();
                connection.commit();
            }catch (SQLException e){
                connection.rollback();
                throw e;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getOrdersByTable(int tableId) {
        List<Order> orders = new ArrayList<Order>();
        Order order = null;

        try(Connection connection = HikariCP.getConnection()){
            PreparedStatement statement = connection.prepareStatement(ORDERS_BY_TABLEID);
            statement.setInt(1,tableId);
            ResultSet setWithOrders = statement.executeQuery();
            ResultSet resultSetDishes = null;
            while (setWithOrders.next()){
                order = EntityBuilder.buildOrder(setWithOrders);
                statement = connection.prepareStatement(DISHES_FROM_ORDER);
                statement.setLong(1, order.getId());
                resultSetDishes = statement.executeQuery();
                order.setDishes(EntityBuilder.buildDishes(resultSetDishes));
                orders.add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return orders;
    }

}
