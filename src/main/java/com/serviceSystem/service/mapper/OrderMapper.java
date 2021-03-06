package com.serviceSystem.service.mapper;

import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.dto.OrderDto;
import com.serviceSystem.entity.dto.form.CreatingOrderForm;
import com.serviceSystem.entity.enums.Status;
import com.serviceSystem.service.OrderService;
import org.hibernate.Hibernate;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper extends AbstractMapper<Order, OrderDto> {

    private OrderService orderService;
    private ModelMapper mapper;
    private DishInOrderMapper dishInOrderMapper;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    @Autowired
    public OrderMapper(OrderService orderService, ModelMapper mapper, DishInOrderMapper dishInOrderMapper) {
        super(Order.class,OrderDto.class);
        this.orderService = orderService;
        this.mapper = mapper;
        this.dishInOrderMapper = dishInOrderMapper;
    }

    @PostConstruct
    private void setupMapper(){
        mapper.createTypeMap(Order.class,OrderDto.class)
                .addMappings(m -> m.skip(OrderDto::setStatus))
                .addMappings(m -> m.skip(OrderDto::setCreationTime))
                .addMappings(m -> m.skip(OrderDto::setBookingTimeBegin))
                .addMappings(m -> m.skip(OrderDto::setBookingTimeEnd))
                .addMappings(m -> m.skip(OrderDto::setDishesInOrder))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(OrderDto.class,Order.class)
                .addMappings(m -> m.skip(Order::setStatus))
                .addMappings(m -> m.skip(Order::setCreationTime))
                .addMappings(m -> m.skip(Order::setBookingTimeBegin))
                .addMappings(m -> m.skip(Order::setBookingTimeEnd))
                .addMappings(m -> m.skip(Order::setDishesInOrder))
                .setPostConverter(toEntityConverter());
    }
    public Order toEntity(CreatingOrderForm creatingOrderForm){
        return creatingOrderForm == null ? null : mapper.map(creatingOrderForm,Order.class);
    }
//    public OrderDto toDtoWithDishesInOrder(Order order){
//        Hibernate.isPropertyInitialized()
//    }

//    public List<OrderDto> toListDtoWithDishesInOrder(List<Order> orders){
//        return orders.stream()
//                .map(order -> {
//                    OrderDto orderDto = toDto(order);
//                    orderDto.setDishesInOrder(dishInOrderMapper.toDtoList(order.getDishesInOrder()));
//                    return orderDto;
//                }).collect(Collectors.toList());
//    }
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
        destination.setBookingTimeBegin(source.getBookingTimeBegin().format(formatter));
        destination.setBookingTimeEnd(source.getBookingTimeEnd().format(formatter));
        if(Hibernate.isInitialized(source.getDishesInOrder())){
            destination.setDishesInOrder(dishInOrderMapper.toDtoList(source.getDishesInOrder()));
        }
    }
    private void mapSpecificFields(OrderDto source, Order destination){
        if(source.getStatus() != null){
            destination.setStatus(Status.valueOf(source.getStatus()));
        }
        if(source.getCreationTime() != null){
            destination.setCreationTime( LocalDateTime.parse(source.getCreationTime(),formatter));
        }
        destination.setBookingTimeBegin(LocalDateTime.parse(source.getBookingTimeBegin(),formatter));
        destination.setBookingTimeEnd(LocalDateTime.parse(source.getBookingTimeEnd(),formatter));
        if(source.getDishesInOrder() != null){
            destination.setDishesInOrder(dishInOrderMapper.toEntityList(source.getDishesInOrder()));
        }
        
    }
}
