package com.serviceSystem.controller;

import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.dto.DishDto;
import com.serviceSystem.exception.NotCorrespondingIdException;
import com.serviceSystem.service.DishService;
import com.serviceSystem.service.mapper.DishMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DishController {

    private Logger logger = LoggerFactory.getLogger(DishController.class);

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
    @PostMapping("/dishes")
    public ResponseEntity addDish(@RequestBody @Valid DishDto dishDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        Dish dish = dishMapper.toEntity(dishDto);
        dishService.save(dish);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PutMapping("/dishes/{id}")
    public ResponseEntity updateDish(@PathVariable("id") int id, @RequestBody @Valid DishDto dishDto,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        if(id != dishDto.getId()){
            throw new NotCorrespondingIdException("Id in json doesn't correspond id in url");
        }
        Dish dish = dishMapper.toEntity(dishDto);
        dishService.update(dish);
        return new ResponseEntity(HttpStatus.OK);
    }

}
