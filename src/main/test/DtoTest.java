import com.serviceSystem.DTO.OrderDTO;
import com.serviceSystem.entity.Order;
import com.serviceSystem.service.OrderService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

public class DtoTest {
    @Test
    public void ConvertToDto(){
        ModelMapper modelMapper = new ModelMapper();
        Order order = OrderService.getInstance().getOrderById(32);
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        assertNotEquals(orderDTO.getCreationTime(),order.getCreationTime().toString());
    }
}
