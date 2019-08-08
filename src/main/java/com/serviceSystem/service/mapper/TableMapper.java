package com.serviceSystem.service.mapper;

import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.entity.dto.OrderedTime;
import com.serviceSystem.entity.dto.TableDto;
import com.serviceSystem.entity.dto.TableDtoWithSchedule;
import com.serviceSystem.service.OrderService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TableMapper extends AbstractMapper<RestaurantTable, TableDto> {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private OrderService orderService;

    public TableMapper(){
        super(RestaurantTable.class, TableDto.class);
    }

    @PostConstruct
    private void setupMapper(){
        mapper.createTypeMap(TableDto.class,RestaurantTable.class)
                .addMappings(m -> m.skip(RestaurantTable::setOrder));
        mapper.createTypeMap(RestaurantTable.class, TableDtoWithSchedule.class)
                .addMappings(m -> m.skip(TableDtoWithSchedule::setSchedule))
                .setPostConverter(toTableDtoWithScheduleConverter());
    }

    private Converter<RestaurantTable,TableDtoWithSchedule> toTableDtoWithScheduleConverter(){
        return mappingContext -> {
            RestaurantTable source = mappingContext.getSource();
            TableDtoWithSchedule destination = mappingContext.getDestination();
            mapSpecificFields(source,destination);
            return destination;
        };
    }

    private void mapSpecificFields(RestaurantTable source, TableDtoWithSchedule destination){
        List<OrderedTime> schedule = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String begin;
        String end;
        for (Order order : orderService.getActiveForTable(source.getId())) {
            begin = formatter.format(order.getBookingTimeBegin());
            end = formatter.format(order.getBookingTimeEnd());
            OrderedTime orderedTime = new OrderedTime(begin,end);
            schedule.add(orderedTime);
        }
        destination.setSchedule(schedule);
    }

    public TableDtoWithSchedule toDtoWithSchedule(RestaurantTable table){
        return table == null ? null : mapper.map(table,TableDtoWithSchedule.class);
    }
    public List<TableDtoWithSchedule> toListOfTableDtoWithSchedule(List<RestaurantTable> tables){
//        if (tables == null){
//            return null;
//        }
        if(tables == null || tables.size() == 0){
            return new ArrayList<>();
        }
        return tables.stream().map(t -> toDtoWithSchedule(t)).collect(Collectors.toList());
    }
}
