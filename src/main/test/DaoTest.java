import com.serviceSystem.entity.Order;
import com.serviceSystem.service.ServiceSystem;
import org.junit.Test;

public class DaoTest {
    @Test
    public void insertOrderTest(){
        ServiceSystem serviceSystem = new ServiceSystem();
        Order order = new Order(serviceSystem.getTables().get(0),serviceSystem.getDishes());
        System.out.println(order);
        serviceSystem.takeOrder(order);
    }
}
