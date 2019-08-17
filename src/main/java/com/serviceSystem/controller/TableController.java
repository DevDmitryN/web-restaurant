package com.serviceSystem.controller;

import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.entity.dto.TableDto;
import com.serviceSystem.exception.NotCorrespondingIdException;
import com.serviceSystem.service.TableService;
import com.serviceSystem.service.mapper.TableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api")
public class TableController {

    @Autowired
    private TableService tableService;
    @Autowired
    private TableMapper tableMapper;

    @GetMapping("/tables")
    public ResponseEntity<List<TableDto>> getTables(Model model){
        List<TableDto> tables = tableMapper.toDtoList(tableService.getAll());
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    @GetMapping("/tables/{tableId}")
    public ResponseEntity<TableDto> getTable(@PathVariable("tableId") int tableId){
        return new ResponseEntity<>(tableMapper.toDto(tableService.getById(tableId)),HttpStatus.OK);
    }

    @PostMapping("/tables")
    public ResponseEntity addTable(@RequestBody @Valid TableDto tableDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        RestaurantTable table = tableMapper.toEntity(tableDto);
        tableService.save(table);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/tables/{tableId}")
    public ResponseEntity updateTable(@RequestBody @Valid TableDto tableDto, BindingResult bindingResult,
                                      @PathVariable("tableId") int tableId){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        if(tableId != tableDto.getId()){
            throw new NotCorrespondingIdException("Table id in path doesn't correspond id in json");
        }
        RestaurantTable table = tableMapper.toEntity(tableDto);
        tableService.update(table);
        return new ResponseEntity(HttpStatus.OK);
    }
}
