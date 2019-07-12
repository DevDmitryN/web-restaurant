import com.serviceSystem.DTO.OrderDTO;
import com.serviceSystem.service.OrderService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DtoTest {
    @Test
    public void ConvertToDto(){
        OrderDTO orderDTO = OrderDTO.toOrderDTO(OrderService.getInstance().getById(5));
        assertEquals("Выполнен",orderDTO.getStatus());
    }
}
