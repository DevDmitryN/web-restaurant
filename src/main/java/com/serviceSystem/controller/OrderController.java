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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Qualifier(value = "creatingOrderFormValidator")
    @Autowired
    private Validator orderFormValidator;

    private DtoConvertrer dishDtoConverter = new DtoConverterImpl(Dish.class,DishDTO.class);
    private DtoConvertrer tableDtoConverter = new DtoConverterImpl(RestaurantTable.class,TableDTO.class);


    @GetMapping("creating")
    public String creating(Model model){
        CreatingOrderForm creatingOrderForm = new CreatingOrderForm();
        creatingOrderForm.setDishes(dishDtoConverter.toDTOList(dishService.getWhichAreInMenu()));
        creatingOrderForm.setTables(tableDtoConverter.toDTOList(tableService.getAll()));
        creatingOrderForm.setYear(LocalDate.now().getYear());
        model.addAttribute("creatingOrderForm", creatingOrderForm);
        return "creatingOrder";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder){
//        binder.setValidator(orderFormValidator);
        binder.addValidators(orderFormValidator);
    }

    @PostMapping("creating")
    public String save( @ModelAttribute("creatingOrderForm") @Valid CreatingOrderForm creatingOrderForm,BindingResult bindingResult
            ,Model model, Principal principalUser ){
        if(bindingResult.hasErrors()){
            logger.info("Binding result " + bindingResult.getAllErrors());
            creatingOrderForm.setDishes(dishDtoConverter.toDTOList(dishService.getWhichAreInMenu()));
            creatingOrderForm.setTables(tableDtoConverter.toDTOList(tableService.getAll()));
            return "creatingOrder";
        }
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
        order.setCreationTime(LocalDateTime.now());
        order.setBookingTime(creatingOrderForm.getBookingTimeFromFields());
        orderService.save(order);
        return "redirect: success";
    }
    @GetMapping("success")
    public String getSuccessPage(){
        return "success";
    }
}
