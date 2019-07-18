package com.serviceSystem.controller;

import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.Order;
import com.serviceSystem.service.ClientService;
import com.serviceSystem.service.DishService;
import com.serviceSystem.service.TableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
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

    @GetMapping("creating")
    public String creating(Model model){
        model.addAttribute("dishes",dishService.getAll());
        model.addAttribute("tables",tableService.getAll());
        model.addAttribute("year", LocalDate.now().getYear());
        return "makeOrder";
    }
    @PostMapping("creating")
    public String save(Model model, @ModelAttribute("dishes") List<Dish> dishes,@ModelAttribute("table_id") Integer tableId, Principal principalUser){

        for (Dish dish : dishes) {
            logger.info(dish.getName() +  " " + dish.getAmount());
        }
        logger.info("table id " + tableId);
        Client client = clientService.getByEmail(principalUser.getName());
        return "success";
    }
}
