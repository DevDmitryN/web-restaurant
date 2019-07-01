import com.serviceSystem.DAO.DAOImpl.*;
import com.serviceSystem.DAO.DAOInterface.OrderDAO;
import com.serviceSystem.entity.*;
import com.serviceSystem.service.ClientService;
import com.serviceSystem.service.OrderService;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class DaoTest {


    @Test
    public void updateTableStatus(){
        RestaurantTableDAOImpl restaurantTableDaoImpl = new RestaurantTableDAOImpl();
        restaurantTableDaoImpl.updateFreeStatus(1,true);
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
    @Test
    public void getAllOrders(){
        OrderDAO orderDAO = new OrderDaoJDBCImpl();
        List<Order> orders = orderDAO.getAll();
        orders.forEach( o -> System.out.println(o.getId() + " creation time: " + o.getWorker()));
    }
}
