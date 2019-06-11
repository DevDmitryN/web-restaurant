import com.serviceSystem.dao.ClientDao;
import com.serviceSystem.dao.OrderDao;
import com.serviceSystem.dao.RestaurantTableDao;
import com.serviceSystem.dao.WorkerDao;
import com.serviceSystem.entity.*;
import com.serviceSystem.entity.enums.Roles;
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

        Client client = new ClientDao().getClientById(2);

        Order order = new Order(table,orderedDishes,client,null);
        OrderDao orderDao = new OrderDao();
        orderDao.insert(order);
    }
    @Test
    public void getClientOrderList(){
        Client client = new ClientDao().getClientById(1);
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
        Worker worker = new Worker(name,surname,password,email,phoneNumber, Roles.ADMIN);
        serviceSystem.addWorker(worker);
    }
    @Test
    public void addOrderWithClientAndWorker(){
        ServiceSystem serviceSystem = ServiceSystem.getInstance();

        RestaurantTable table = serviceSystem.getTables().get(1);
        List<Dish> dishes = serviceSystem.getDishes();
        List<Dish> orderedDishes = new ArrayList<>();
        orderedDishes.add(dishes.get(0));
        orderedDishes.add(dishes.get(1));

        Client client = new ClientDao().getClientById(2);
        Worker worker = new WorkerDao().getWorkerById(1);
        Order order = new Order(table,orderedDishes,client,worker);

        new OrderDao().insert(order);
    }
    @Test
    public void updateTableStatus(){
        RestaurantTableDao restaurantTableDao = new RestaurantTableDao();
        restaurantTableDao.updateFreeStatus(1,true);
    }
}
