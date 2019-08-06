package com.serviceSystem.service.mapper;

import com.serviceSystem.entity.DishInOrder;
import com.serviceSystem.entity.dto.DishInOrderDto;
import com.serviceSystem.service.OrderService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DishInOrderMapper extends AbstractMapper<DishInOrder, DishInOrderDto>{

    private ModelMapper mapper;
    private OrderService orderService;

    @Autowired
    public DishInOrderMapper(ModelMapper mapper, OrderService orderService) {
        super(DishInOrder.class, DishInOrderDto.class);
        this.mapper = mapper;
        this.orderService = orderService;
    }
    @PostConstruct
    public void setupMapper(){
        mapper.createTypeMap(DishInOrder.class, DishInOrderDto.class);
//                .addMappings(m -> m.skip(DishInOrderDto::setOrderId))
//                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(DishInOrderDto.class, DishInOrder.class)
                .addMappings(m -> m.skip(DishInOrder::setOrder))
                .addMappings(m -> m.skip(DishInOrder::setId));
//                .setPostConverter(toEntityConverter());
    }

//    private Converter<DishInOrder, DishInOrderDto> toDtoConverter(){
//        return mappingContext -> {
//            DishInOrder source = mappingContext.getSource();
//            DishInOrderDto destination = mappingContext.getDestination();
//            mapSpecificFields(source,destination);
//            return mappingContext.getDestination();
//        };
//    }
//    private Converter<DishInOrderDto, DishInOrder> toEntityConverter(){
//        return mappingContext -> {
//            DishInOrderDto source = mappingContext.getSource();
//            DishInOrder destination = mappingContext.getDestination();
//            mapSpecificFields(source,destination);
//            return mappingContext.getDestination();
//        };
//    }
//    private void mapSpecificFields(DishInOrder source, DishInOrderDto destination){
//        destination.setOrderId(source.getId());
//    }
//    private void mapSpecificFields(DishInOrderDto source, DishInOrder destination){
//        destination.setOrder(orderService.getById(source.getOrderId()));
//        // Skip setting OrderDish.id
//    }
}
