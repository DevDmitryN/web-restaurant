import com.serviceSystem.DAO.DAOImpl.*;
import com.serviceSystem.DAO.DAOInterface.OrderDAO;
import com.serviceSystem.DAO.connectionPool.HikariCP;
import com.serviceSystem.entity.*;
import com.serviceSystem.entity.enums.Role;
import com.serviceSystem.service.ServiceSystem;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DaoTest {
    @Test
    public void insertOrderWithJDBC(){
        ServiceSystem serviceSystem = ServiceSystem.getInstance();
    }
    @Test
    public void addClient(){
        ServiceSystem serviceSystem = ServiceSystem.getInstance();
        String name = "boris";
        String surname = "britva";
        String password = "7788";
        String email = "mqeq@email.com";
        String phoneNumber = "+37551231212";
        String cardNumber = "1234-wefaf-12sfd";
        Client client = new Client(name,surname,password,email,phoneNumber,cardNumber);
        serviceSystem.addClient(client);
    }
    @Test
    public void addOrderWithClient(){
        ServiceSystem serviceSystem = ServiceSystem.getInstance();
        RestaurantTable table = serviceSystem.getTables().get(1);
        List<Dish> dishes = serviceSystem.getDishes();
        List<Dish> orderedDishes = new ArrayList<>();
        orderedDishes.add(dishes.get(0));
        orderedDishes.add(dishes.get(1));

        Client client = new ClientDAOImpl().getById(2);

        Order order = new Order(table,orderedDishes,client,null);
        OrderDAOImpl orderDao = new OrderDAOImpl();
        orderDao.save(order);
    }
    @Test
    public void getClientOrderList(){
        Client client = new ClientDAOImpl().getById(1);
        //client has empty list of orders
        for (Order order : client.getOrders()) {
            System.out.println(order);
        }
    }
    @Test
    public void addWorker(){
        ServiceSystem serviceSystem = ServiceSystem.getInstance();
        String name = "vladimir";
        String surname = "putin";
        String password = "1954";
        String email = "putin@email.com";
        String phoneNumber = "+375413412";
        String cardNumber = "0000-wefaf-12sfd";
        Worker worker = new Worker(name,surname,password,email,phoneNumber, Role.ADMIN);
        serviceSystem.addWorker(worker);
    }
    @Test
    public void addOrderWithClientAndWorker(){
        OrderDAO orderDAO = new OrderDaoJDBCImpl();
        ServiceSystem serviceSystem = ServiceSystem.getInstance();
        RestaurantTable table = serviceSystem.getTables().get(1);
        List<Dish> dishes = serviceSystem.getDishes();
        List<Dish> orderedDishes = new ArrayList<>();
        orderedDishes.add(dishes.get(2));
        orderedDishes.add(dishes.get(3));
        orderedDishes.add(dishes.get(5));

        Client client = new ClientDAOImpl().getById(2);
        Worker worker = new WorkerDAOImpl().getById(1);
        Order order = new Order(table,orderedDishes,client,worker);

        orderDAO.save(order);
    }
    @Test
    public void updateTableStatus(){
        RestaurantTableDAOImpl restaurantTableDaoImpl = new RestaurantTableDAOImpl();
        restaurantTableDaoImpl.updateFreeStatus(1,true);
    }
    @Test
    public void hikariCPTest(){
        OrderDaoJDBCImpl orderDaoJDBC = new OrderDaoJDBCImpl();
        List<Order> orders = orderDaoJDBC.getAll();
        orders.forEach( a -> System.out.println("id: " + a.getId() + ",total price = " + a.getTotalPrice()));
    }
    @Test
    public void getOrderById(){
        OrderDAO orderDAO = new OrderDaoJDBCImpl();
        Order order = orderDAO.getById(40);
        System.out.println(order.getId());
        order.getDishes().forEach(d -> System.out.println(d));
    }
    @Test
    public void getOrdersByTableId(){
        OrderDAO orderDAO = new OrderDaoJDBCImpl();
        List<Order> orders = orderDAO.getOrdersByTable(2);
        orders.get(0).getDishes().forEach( o -> System.out.println(o));
    }
}
