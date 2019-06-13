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

//    Properties properties;
//    String url;
//    String username;
//    String password;
//
//    //doesnt work
//    public OrderDaoJDBCImpl(Properties properties){
//        this.properties = properties;
//        url = properties.getProperty("url");
//        username = properties.getProperty("username");;
//        password = properties.getProperty("password");
//    }
//
//    public OrderDaoJDBCImpl(String url, String username, String password) {
//        this.url = url;
//        this.username = username;
//        this.password = password;
//    }

    @Override
    public void save(Order order){
        try(Connection connection = HikariCP.getConnection();
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

    @Override
    public void update(Order entity) {

    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<Order>();
        try(Connection connection = HikariCP.getConnection()){
//            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            Statement statement = connection.createStatement();
            ResultSet resultSetOrders = statement.executeQuery(SELECT_ALL);
            while (resultSetOrders.next()){
                List<Dish> dishes = new ArrayList<Dish>();
                PreparedStatement preparedStatement = connection.prepareStatement(DISHES_FROM_ORDER);
                preparedStatement.setInt(1,resultSetOrders.getInt(1));
                ResultSet resultSetDishes =  preparedStatement.executeQuery();
                Order order = EntityBuilder.buildOrder(resultSetOrders);
                while (resultSetDishes.next()){
                    dishes.add(EntityBuilder.buildDish(resultSetDishes));
                }
                order.setDishes(dishes);
                orders.add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order getById(long id) {
        return null;
    }
}
