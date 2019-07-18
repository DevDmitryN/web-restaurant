package com.serviceSystem.controller;


import com.serviceSystem.entity.DTO.OrderDTO;
import com.serviceSystem.entity.Order;
import com.serviceSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/")
@Controller
public class TestController {

    @Autowired
    private OrderService orderService;

//    @GetMapping
//    public String index(){
//        return "index";
//    }
    @GetMapping("list")
    public String list(Model model){
        List<Order> orders = orderService.getAll();
        List<OrderDTO> ordersDTO = OrderDTO.toListOfOrderDTO(orders);
        model.addAttribute("orders",ordersDTO);
        return "showOrders";
    }
}
