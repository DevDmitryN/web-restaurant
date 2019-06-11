import com.serviceSystem.dao.OrderDao;
import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.service.ServiceSystem;
import org.junit.Test;

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
        String name = "robert";
        String surname = "snow";
        String password = "1234";
        String email = "mail@email.com";
        String phoneNumber = "+375441231212";
        String cardNumber = "1234-adfaf-12sfd";
        Client client = new Client(name,surname,password,email,phoneNumber,cardNumber);
        serviceSystem.addClient(client);
    }
    @Test
    public void addOrderWithHibernate(){
        ServiceSystem serviceSystem = ServiceSystem.getInstance();
        RestaurantTable table = serviceSystem.getTables().get(1);
        List<Dish> dishes = serviceSystem.getDishes();
        List<Dish> orderedDishes = new ArrayList<>();
        orderedDishes.add(dishes.get(2));
        orderedDishes.add(dishes.get(5));
        Order order = new Order(table,orderedDishes);
        OrderDao orderDao = new OrderDao();
        orderDao.insert(order);
    }
}
