package com.serviceSystem.DAO.DAOImpl;

import com.serviceSystem.DAO.DAOInterface.OrderDAO;
import com.serviceSystem.DAO.builder.EntityBuilder;
import com.serviceSystem.DAO.connectionPool.HikariCP;
import com.serviceSystem.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class OrderDaoJDBCImpl implements OrderDAO {
    private static final Logger logger = LoggerFactory.getLogger(OrderDaoJDBCImpl.class);


    private final String SELECT_ALL = "select o.id,o.status,o.creation_time,o.booking_time," +
            "t.id as t_id,t.capacity as t_capacity,t.is_free as t_is_free," +
            "c.id as c_id,c.name as c_name,c.surname as c_surname,c.email as c_email,c.phone_number as c_phone_number,c.card_number as c_card_number," +
            "w.id as w_id,w.name as w_name,w.surname as w_surname,w.email as w_email,w.phone_number as w_phone_number,w.role as w_role " +
            "from restaurantdb.orders as o " +
            "join restaurantdb.\"tables\" as t on o.table_id = t.id " +
            "join restaurantdb.clients as c on o.client_id = c.id " +
            "left join restaurantdb.workers as w on o.worker_id = w.id";
    private final String DISHES_FROM_ORDER = "select d.id,name,price,description from restaurantdb.order_dish as od\n" +
            "join restaurantdb.dishes as d on od.dish_id=d.id\n" +
            "where od.order_id=?";
    private final String ORDER_BY_ID = SELECT_ALL + " where o.id=?";
    private final String INSERT_INTO_ORDERS = "INSERT INTO restaurantdb.orders (table_id,client_id,creation_time,booking_time) VALUES (?,?,?,?) RETURNING id";
    private final String INSERT_INTO_ORDER_DISH = "INSERT INTO restaurantdb.order_dish (order_id, dish_id) VALUES (?,?)";
    private final String DELETE_FROM_ORDERS = "delete from restaurantdb.orders where id=?";
    private final String ORDERS_BY_TABLEID = SELECT_ALL + " where o.table_id=?";
    private final String UPDATE_ORDER = "UPDATE restaurantdb.orders set table_id = ?, worker_id = ?, booking_time = ? where id = ?";
    @Override
    public void save(Order order) {
        try (Connection connection = HikariCP.getConnection();
            PreparedStatement statementForOrder = connection.prepareStatement(INSERT_INTO_ORDERS);
            PreparedStatement statementForDishes = connection.prepareStatement(INSERT_INTO_ORDER_DISH)) {
            try {
                connection.setAutoCommit(false);
                statementForOrder.setInt(1, order.getTable().getId());
                statementForOrder.setLong(2, order.getClient().getId());
                statementForOrder.setTimestamp(3,Timestamp.valueOf(order.getCreationTime()));
                statementForOrder.setTimestamp(4,Timestamp.valueOf(order.getBookingTime()));
                ResultSet setOfId = statementForOrder.executeQuery();
                long id = setOfId.next() ? setOfId.getLong(1) : -1;
                for (Dish dish : order.getDishes()) {
                    statementForDishes.setLong(1, id);
                    statementForDishes.setInt(2, dish.getId());
                    statementForDishes.executeUpdate();
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
        try (Connection connection = HikariCP.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER);) {
            try{
                connection.setAutoCommit(false);
                statement.setInt(1,order.getTable().getId());
                statement.setLong(2,order.getWorker().getId());
                statement.setTimestamp(3,Timestamp.valueOf(order.getBookingTime()));
                statement.setLong(4,order.getId());
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
    public List<Order> getAll() {
        logger.warn("start");
        List<Order> orders = new ArrayList<Order>();
        try (Connection connection = HikariCP.getConnection();
             Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement(DISHES_FROM_ORDER);) {
            ResultSet setOfOrders = statement.executeQuery(SELECT_ALL);
            ResultSet setOfDishes;
            while (setOfOrders.next()) {
                preparedStatement.setInt(1, setOfOrders.getInt(1));
                setOfDishes = preparedStatement.executeQuery();
                Order order = EntityBuilder.buildOrder(setOfOrders);
                order.setDishes(EntityBuilder.buildDishes(setOfDishes));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("finish");
        return orders;

    }

    @Override
    public Order getById(long id) {
        Order order = null;
        try (Connection connection = HikariCP.getConnection();
             PreparedStatement statementForOrder = connection.prepareStatement(ORDER_BY_ID);
             PreparedStatement statementForDishes = connection.prepareStatement(DISHES_FROM_ORDER);) {
            statementForOrder.setLong(1, id);
            ResultSet setOfOrders = statementForOrder.executeQuery();
            if (setOfOrders.next()) {
                order = EntityBuilder.buildOrder(setOfOrders);
                statementForDishes.setLong(1, id);
                ResultSet setOfDishes = statementForDishes.executeQuery();
                order.setDishes(EntityBuilder.buildDishes(setOfDishes));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void delete(long id){
        try(Connection connection = HikariCP.getConnection();
            PreparedStatement statementForOrder = connection.prepareStatement(DELETE_FROM_ORDERS);){
            try {
                connection.setAutoCommit(false);
                statementForOrder.setLong(1, id);
                statementForOrder.executeUpdate();
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
        Order order;
        try(Connection connection = HikariCP.getConnection();
            PreparedStatement statementForOrders = connection.prepareStatement(ORDERS_BY_TABLEID);
            PreparedStatement statementForDishes = connection.prepareStatement(DISHES_FROM_ORDER);){
            statementForOrders.setInt(1,tableId);
            ResultSet setOfOrders = statementForOrders.executeQuery();
            ResultSet setOfDishes;
            while (setOfOrders.next()){
                order = EntityBuilder.buildOrder(setOfOrders);
                statementForDishes.setLong(1, order.getId());
                setOfDishes = statementForDishes.executeQuery();
                order.setDishes(EntityBuilder.buildDishes(setOfDishes));
                orders.add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return orders;
    }
}
