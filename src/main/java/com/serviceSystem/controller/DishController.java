package com.serviceSystem.controller;

import com.serviceSystem.entity.dto.DishDto;
import com.serviceSystem.service.DishService;
import com.serviceSystem.service.mapper.DishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private DishMapper dishMapper;

    @GetMapping("/menu")
    public ResponseEntity<List<DishDto>> getMenu(){
        return new ResponseEntity<>(dishMapper.toDtoList(dishService.getWhichAreInMenu()), HttpStatus.OK);
    }

    @GetMapping("/dishes/{dishId}")
    public ResponseEntity<DishDto> getById(@PathVariable("dishId") int dishId){
        return new ResponseEntity<>(dishMapper.toDto(dishService.getById(dishId)),HttpStatus.OK);
    }

    @GetMapping("/dishes")
    public ResponseEntity<List<DishDto>> getAll(){
        return new ResponseEntity<>(dishMapper.toDtoList(dishService.getAll()),HttpStatus.OK);
    }

}
