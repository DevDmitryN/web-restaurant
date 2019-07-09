import com.serviceSystem.DTO.OrderDTO;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.enums.Status;
import com.serviceSystem.service.OrderService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DtoTest {
    @Test
    public void ConvertToDto(){
        OrderDTO orderDTO = OrderDTO.toOrderDTO(OrderService.getInstance().getOrderById(5));
        assertEquals("Выполнен",orderDTO.getStatus());
    }
}
