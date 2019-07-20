package com.serviceSystem.controller;

import com.serviceSystem.controller.util.CreatingOrderForm;
import com.serviceSystem.controller.util.dtoConverter.DtoConverterImpl;
import com.serviceSystem.controller.util.dtoConverter.DtoConvertrer;
import com.serviceSystem.entity.*;
import com.serviceSystem.entity.DTO.DishDTO;
import com.serviceSystem.entity.DTO.TableDTO;
import com.serviceSystem.service.ClientService;
import com.serviceSystem.service.DishService;
import com.serviceSystem.service.OrderService;
import com.serviceSystem.service.TableService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("orders")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private DishService dishService;
    @Autowired
    private TableService tableService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private OrderService orderService;

    private DtoConvertrer dishDtoConverter = new DtoConverterImpl(Dish.class,DishDTO.class);
    private DtoConvertrer tableDtoConverter = new DtoConverterImpl(RestaurantTable.class,TableDTO.class);
    @GetMapping("creating")
    public String creating(Model model){
        CreatingOrderForm creatingOrderForm = new CreatingOrderForm();
        creatingOrderForm.setDishes(dishDtoConverter.toDTOList(dishService.getAll()));
        creatingOrderForm.setTables(tableDtoConverter.toDTOList(tableService.getAll()));
        creatingOrderForm.setYear(LocalDate.now().getYear());
        model.addAttribute("creatingOrderForm", creatingOrderForm);
        return "creatingOrder";
    }
    @PostMapping("creating")
    public String save(@ModelAttribute("creatingOrderForm") CreatingOrderForm creatingOrderForm, Principal principalUser){
        Order order = new Order();
        List<OrderDish> orderDishes = new ArrayList<>();
        for (DishDTO dishDTO : creatingOrderForm.getDishes()) {
            if(dishDTO.getAmount() != 0){
                Dish dish = (Dish) dishDtoConverter.fromDTO(dishDTO);
                orderDishes.add(new OrderDish(order,dish,dishDTO.getAmount()));
            }
        }
        order.setOrderDish(orderDishes);
        order.setClient(clientService.getByEmail(principalUser.getName()));
        RestaurantTable table = new RestaurantTable();
        table.setId(creatingOrderForm.getTableId());
        order.setTable(table);
        LocalDateTime creationTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        LocalDateTime bookingTime = LocalDateTime.of(
                creatingOrderForm.getYear(),
                creatingOrderForm.getMonth(),
                creatingOrderForm.getDay(),
                creatingOrderForm.getHour(),
                creatingOrderForm.getMinutes()
        );
        order.setCreationTime(creationTime);
        order.setBookingTime(bookingTime);
        orderService.save(order);
        return "redirect: success";
    }
    @GetMapping("success")
    public String getSuccessPage(){
        return "success";
    }
}
