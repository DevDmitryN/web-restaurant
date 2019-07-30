package com.serviceSystem.service.mapper;

import com.serviceSystem.entity.OrderDish;
import com.serviceSystem.entity.dto.OrderDishDto;
import com.serviceSystem.service.OrderService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class OrderDishMapper  extends AbstractMapper<OrderDish,OrderDishDto>{

    private ModelMapper mapper;
    private OrderService orderService;

    @Autowired
    public OrderDishMapper(ModelMapper mapper, OrderService orderService) {
        super(OrderDish.class,OrderDishDto.class);
        this.mapper = mapper;
        this.orderService = orderService;
    }
    @PostConstruct
    public void setupMapper(){
        mapper.createTypeMap(OrderDish.class,OrderDishDto.class)
                .addMappings(m -> m.skip(OrderDishDto::setOrderId))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(OrderDishDto.class,OrderDish.class)
                .addMappings(m -> m.skip(OrderDish::setOrder))
                .addMappings(m -> m.skip(OrderDish::setId))
                .setPostConverter(toEntityConverter());
    }

    private Converter<OrderDish,OrderDishDto> toDtoConverter(){
        return mappingContext -> {
            OrderDish source = mappingContext.getSource();
            OrderDishDto destination = mappingContext.getDestination();
            mapSpecificFields(source,destination);
            return mappingContext.getDestination();
        };
    }
    private Converter<OrderDishDto,OrderDish> toEntityConverter(){
        return mappingContext -> {
            OrderDishDto source = mappingContext.getSource();
            OrderDish destination = mappingContext.getDestination();
            mapSpecificFields(source,destination);
            return mappingContext.getDestination();
        };
    }
    private void mapSpecificFields(OrderDish source, OrderDishDto destination){
        destination.setOrderId(source.getId());
    }
    private void mapSpecificFields(OrderDishDto source, OrderDish destination){
        destination.setOrder(orderService.getById(source.getOrderId()));
        // Skip setting OrderDish.id
    }
}
