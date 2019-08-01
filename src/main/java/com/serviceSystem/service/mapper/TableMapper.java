package com.serviceSystem.service.mapper;

import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.entity.dto.TableDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TableMapper extends AbstractMapper<RestaurantTable, TableDto> {
    @Autowired
    private ModelMapper mapper;

    public TableMapper(){
        super(RestaurantTable.class, TableDto.class);
    }

    @PostConstruct
    private void setupMapper(){
        mapper.createTypeMap(TableDto.class,RestaurantTable.class)
                .addMappings(m -> m.skip(RestaurantTable::setOrder));
    }
}
