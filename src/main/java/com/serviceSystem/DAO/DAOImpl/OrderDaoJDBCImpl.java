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
    private final String DISHES_FROM_ORDER = "select d.id,name,price,description,od.amount from restaurantdb.order_dish as od " +
            "join restaurantdb.dishes as d on od.dish_id=d.id " +
            "where od.order_id=?";
    private final String ORDER_BY_ID = SELECT_ALL + " where o.id=?";
    private final String INSERT_INTO_ORDERS = "INSERT INTO restaurantdb.orders (table_id,client_id,creation_time,booking_time) VALUES (?,?,?,?) RETURNING id";
    private final String INSERT_INTO_ORDER_DISH = "INSERT INTO restaurantdb.order_dish (order_id, dish_id,amount) VALUES (?,?,?)";
    private final String DELETE_FROM_ORDERS = "delete from restaurantdb.orders where id=?";
    private final String ORDERS_BY_TABLEID = SELECT_ALL + " where o.table_id=?";
    private final String UPDATE_ORDER = "UPDATE restaurantdb.orders set table_id = ?, worker_id = ? , status = CAST(? as status) where id = ?";
    private final String GET_NOT_COMPLETED_BY_CLIENT_ID = SELECT_ALL + " where client_id = ? and status != CAST(\'COMPLETED\' as status)";
    @Override
    public void save(Order order) {
        logger.info("save the order");
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
                if(setOfId.next()){
                    long id = setOfId.getLong(1);
                    for (Dish dish : order.getDishes()) {
                        statementForDishes.setLong(1, id);
                        statementForDishes.setInt(2, dish.getId());
                        statementForDishes.setInt(3,dish.getAmount());
                        statementForDishes.executeUpdate();
                    }
                    connection.commit();
                }else{
                    throw new  SQLException();
                }
            }catch (SQLException e){
                connection.rollback();
                logger.error("can't save the order");
                e.printStackTrace();
            }
        }catch (SQLException e){
            logger.error("can't rollback the transaction");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Order order) {
        logger.info("update the order");
        try (Connection connection = HikariCP.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER);) {
            try{
                connection.setAutoCommit(false);
                statement.setInt(1,order.getTable().getId());
                statement.setLong(2,order.getWorker().getId());
                statement.setString(3,order.getStatus().toString());
                statement.setLong(4,order.getId());
                statement.executeUpdate();
                connection.commit();
            }catch (SQLException e){
                connection.rollback();
                logger.error("can't update the order");
                e.printStackTrace();
            }
        }catch (SQLException e){
            logger.error("can't rollback the transaction");
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAll() {
        logger.info("getting all orders");
//        List<Order> orders = new ArrayList<Order>();
        List<Order> orders = null;
        try (Connection connection = HikariCP.getConnection();
             Statement statement = connection.createStatement();
             PreparedStatement statementForDishes = connection.prepareStatement(DISHES_FROM_ORDER);) {
            ResultSet setOfOrders = statement.executeQuery(SELECT_ALL);
            orders = EntityBuilder.buildOrders(setOfOrders,statementForDishes);
        } catch (SQLException e) {
            logger.error("can't get all orders");
            e.printStackTrace();
        }
        return orders;

    }

    @Override
    public Order getById(long id) {
        logger.info("getting the order by id");
        Order order = null;
        try (Connection connection = HikariCP.getConnection();
             PreparedStatement statementForOrder = connection.prepareStatement(ORDER_BY_ID);
             PreparedStatement statementForDishes = connection.prepareStatement(DISHES_FROM_ORDER);) {
            statementForOrder.setLong(1, id);
            ResultSet setOfOrders = statementForOrder.executeQuery();
            if (setOfOrders.next()) {
                order = EntityBuilder.buildOrder(setOfOrders,statementForDishes);
            }
        } catch (SQLException e) {
            logger.error("can't get the order by id");
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void delete(long id){
        logger.info("deleting the order by id");
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
            logger.error("can't delete the order");
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getByTable(int tableId) {
        logger.info("getting orders by table");
        List<Order> orders = null;
        try(Connection connection = HikariCP.getConnection();
            PreparedStatement statementForOrders = connection.prepareStatement(ORDERS_BY_TABLEID);
            PreparedStatement statementForDishes = connection.prepareStatement(DISHES_FROM_ORDER);){
            statementForOrders.setInt(1,tableId);
            ResultSet setOfOrders = statementForOrders.executeQuery();
            orders = EntityBuilder.buildOrders(setOfOrders,statementForDishes);
        }catch (SQLException e){
            logger.error("can't get orders by table");
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> getNotCompletedByClientId(long clientId) {
        List<Order> orders = null;
        try(Connection connection = HikariCP.getConnection();
            PreparedStatement statementForOrders = connection.prepareStatement(GET_NOT_COMPLETED_BY_CLIENT_ID);
            PreparedStatement statementForDishes = connection.prepareStatement(DISHES_FROM_ORDER)){
            statementForOrders.setLong(1,clientId);
            ResultSet setOfOrders = statementForOrders.executeQuery();
            orders = EntityBuilder.buildOrders(setOfOrders,statementForDishes);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return orders;
    }
}
