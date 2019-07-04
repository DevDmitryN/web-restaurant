import com.serviceSystem.DAO.DAOImpl.*;
import com.serviceSystem.DAO.DAOInterface.OrderDAO;
import com.serviceSystem.entity.*;
import org.junit.jupiter.api.Test;


import java.util.List;


public class DaoTest {

    @Test
    public void test(){
        String url = "/view/index.jsp";
        System.out.println(url.replaceFirst("/view",""));
    }
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
        List<Order> orders = orderDAO.getByTable(2);
        orders.get(0).getDishes().forEach( o -> System.out.println(o));
    }
    @Test
    public void getAllOrders(){
        OrderDAO orderDAO = new OrderDaoJDBCImpl();
        List<Order> orders = orderDAO.getAll();
        orders.forEach( o -> System.out.println(o.getId() + " creation time: " + o.getWorker()));
    }
}
