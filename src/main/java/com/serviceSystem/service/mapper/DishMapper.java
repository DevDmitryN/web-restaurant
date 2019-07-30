package com.serviceSystem.service.mapper;

import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.dto.DishDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DishMapper extends AbstractMapper<Dish,DishDto> {

    @Autowired
    private ModelMapper mapper;

    public DishMapper() {
        super(Dish.class, DishDto.class);
    }
}
