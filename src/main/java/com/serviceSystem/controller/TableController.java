package com.serviceSystem.controller;

import com.serviceSystem.controller.util.dtoConverter.DtoConverterImpl;
import com.serviceSystem.controller.util.dtoConverter.DtoConvertrer;
import com.serviceSystem.entity.DTO.TableDTO;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TableController {

    @Autowired
    private TableService tableService;
    private DtoConvertrer<RestaurantTable, TableDTO> tableDtoConverter = new DtoConverterImpl<>(RestaurantTable.class,TableDTO.class);

    @GetMapping("tables/list")
    public String getTables(Model model){
        List<TableDTO> tables = tableDtoConverter.toDTOList(tableService.getAll());
        model.addAttribute("tables",tables);
        return "tables";
    }
}
