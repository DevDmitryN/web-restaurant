import com.serviceSystem.service.ServiceSystem;
import org.junit.jupiter.api.Test;

public class ServiceTest {
    @Test
    public void getDish(){
        ServiceSystem serviceSystem = ServiceSystem.getInstance();
        serviceSystem.getDishes().forEach(d -> System.out.println(d.getName()));
    }
}
