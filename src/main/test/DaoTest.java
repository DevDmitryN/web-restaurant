import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.Order;
import com.serviceSystem.service.ServiceSystem;
import org.junit.Test;

import java.util.List;

public class DaoTest {
    @Test
    public void insertOrderTest(){
        ServiceSystem serviceSystem = ServiceSystem.getInstance();
    }
    @Test
    public void addClientTest(){
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
}
