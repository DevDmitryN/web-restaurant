package com.serviceSystem.controller;

import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.dto.DishDto;
import com.serviceSystem.exception.BindingResultException;
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
@RequestMapping("/api/v1")
public class DishController {

    private Logger logger = LoggerFactory.getLogger(DishController.class);

    @Autowired
    private DishService dishService;
    @Autowired
    private DishMapper dishMapper;

    @GetMapping("/menu")
    public ResponseEntity<List<DishDto>> getMenu(){
        logger.info("Get menu");
        return new ResponseEntity<>(dishMapper.toDtoList(dishService.getWhichAreInMenu()), HttpStatus.OK);
    }

    @GetMapping("/menu/{dishId}")
    public ResponseEntity getByIdFromMenu(@PathVariable("dishId") int dishId){
        DishDto dishDto = dishMapper.toDto(dishService.getByIdFromMenu(dishId));
        return new ResponseEntity<>(dishDto, HttpStatus.OK);
    }

    @GetMapping("/dishes/{dishId}")
    public ResponseEntity<DishDto> getById(@PathVariable("dishId") int dishId){
        logger.info("Get dish " + dishId);
        return new ResponseEntity<>(dishMapper.toDto(dishService.getById(dishId)),HttpStatus.OK);
    }

    @GetMapping("/dishes")
    public ResponseEntity<List<DishDto>> getAll(){
        return new ResponseEntity<>(dishMapper.toDtoList(dishService.getAll()),HttpStatus.OK);
    }
    @PostMapping("/dishes")
    public ResponseEntity addDish(@RequestBody @Valid DishDto dishDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult.getAllErrors());
        }
        Dish dish = dishMapper.toEntity(dishDto);
        dishService.save(dish);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping("/dishes/{dishId}")
    public ResponseEntity updateDish( @RequestBody @Valid DishDto dishDto,BindingResult bindingResult,
                                      @PathVariable("dishId") int id){
        if(id != dishDto.getId()){
            throw new NotCorrespondingIdException("Id in json doesn't correspond id in url");
        }
        if(bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult.getAllErrors());
        }
        Dish dish = dishMapper.toEntity(dishDto);
        dishService.update(dish);
        return new ResponseEntity(HttpStatus.OK);
    }

}
