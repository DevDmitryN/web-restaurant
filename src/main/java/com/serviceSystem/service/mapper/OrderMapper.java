package com.serviceSystem.service.mapper;

import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.dto.OrderDto;
import com.serviceSystem.entity.enums.Status;
import com.serviceSystem.service.OrderService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper extends AbstractMapper<Order, OrderDto> {

    private OrderService orderService;
    private ModelMapper mapper;
    private OrderDishMapper orderDishMapper;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    @Autowired
    public OrderMapper(OrderService orderService, ModelMapper mapper,OrderDishMapper orderDishMapper) {
        super(Order.class,OrderDto.class);
        this.orderService = orderService;
        this.mapper = mapper;
        this.orderDishMapper = orderDishMapper;
    }

    @PostConstruct
    private void setupMapper(){
        mapper.createTypeMap(Order.class,OrderDto.class)
                .addMappings(m -> m.skip(OrderDto::setStatus))
                .addMappings(m -> m.skip(OrderDto::setCreationTime))
                .addMappings(m -> m.skip(OrderDto::setBookingTime))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(OrderDto.class,Order.class)
                .addMappings(m -> m.skip(Order::setStatus))
                .addMappings(m -> m.skip(Order::setCreationTime))
                .addMappings(m -> m.skip(Order::setBookingTime))
                .setPostConverter(toEntityConverter());
    }
    private Converter<Order,OrderDto> toDtoConverter(){
        return mappingContext -> {
            Order source = mappingContext.getSource();
            OrderDto destination = mappingContext.getDestination();
            mapSpecificFields(source,destination);
            return mappingContext.getDestination();
        };
    }
    private Converter<OrderDto,Order> toEntityConverter(){
        return mappingContext -> {
            OrderDto source = mappingContext.getSource();
            Order destination = mappingContext.getDestination();
            mapSpecificFields(source,destination);
            return mappingContext.getDestination();
        };
    }
    private void mapSpecificFields(Order source,OrderDto destination){
        destination.setStatus(source.getStatus().name());
        destination.setCreationTime(source.getCreationTime().format(formatter));
        destination.setBookingTime(source.getBookingTime().format(formatter));
    }
    private void mapSpecificFields(OrderDto source, Order destination){
        destination.setStatus(Status.valueOf(source.getStatus()));
        destination.setCreationTime(LocalDateTime.parse(source.getCreationTime(),formatter));
        destination.setCreationTime(LocalDateTime.parse(source.getBookingTime(),formatter));
    }

}
