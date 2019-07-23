package com.serviceSystem.controller;

import com.serviceSystem.controller.util.CreatingOrderForm;
import com.serviceSystem.controller.util.dtoConverter.DtoConverterImpl;
import com.serviceSystem.controller.util.dtoConverter.DtoConvertrer;
import com.serviceSystem.entity.*;
import com.serviceSystem.entity.DTO.DishDTO;
import com.serviceSystem.entity.DTO.OrderDTO;
import com.serviceSystem.entity.DTO.TableDTO;
import com.serviceSystem.service.*;
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
    @Autowired
    private WorkerService workerService;
    @Qualifier(value = "creatingOrderFormValidator")
    @Autowired
    private Validator orderFormValidator;

    private DtoConvertrer<Dish,DishDTO> dishDtoConverter = new DtoConverterImpl(Dish.class, DishDTO.class);
    private DtoConvertrer<RestaurantTable,TableDTO> tableDtoConverter = new DtoConverterImpl(RestaurantTable.class, TableDTO.class);
    private DtoConvertrer<Order,OrderDTO> orderDtoConverter = new DtoConverterImpl(Order.class, OrderDTO.class);


    @GetMapping("creating")
    public String creating(Model model) {
        logger.info("Get page for creating order");
        CreatingOrderForm creatingOrderForm = new CreatingOrderForm();
        creatingOrderForm.setDishes(dishDtoConverter.toDTOList(dishService.getWhichAreInMenu()));
        creatingOrderForm.setTables(tableDtoConverter.toDTOList(tableService.getAll()));
        creatingOrderForm.setYear(LocalDate.now().getYear());
        model.addAttribute("creatingOrderForm", creatingOrderForm);
        return "creatingOrder";
    }


    @PostMapping("creating")
    public String save(@ModelAttribute("creatingOrderForm") @Valid CreatingOrderForm creatingOrderForm, BindingResult bindingResult
            , Model model, Principal principalUser) {
        orderFormValidator.validate(creatingOrderForm, bindingResult);
        if (bindingResult.hasErrors()) {
            logger.info("Binding result " + bindingResult.getAllErrors());
            creatingOrderForm.setDishes(dishDtoConverter.toDTOList(dishService.getWhichAreInMenu()));
            creatingOrderForm.setTables(tableDtoConverter.toDTOList(tableService.getAll()));
            return "creatingOrder";
        }
        Order order = new Order();
        List<OrderDish> orderDishes = new ArrayList<>();
        for (DishDTO dishDTO : creatingOrderForm.getDishes()) {
            if (dishDTO.getAmount() != 0) {
                Dish dish = dishDtoConverter.fromDTO(dishDTO);
                orderDishes.add(new OrderDish(order, dish, dishDTO.getAmount()));
            }
        }
        order.setOrderDish(orderDishes);
        order.setClient(clientService.getByEmail(principalUser.getName()));
        RestaurantTable table = new RestaurantTable();
        table.setId(creatingOrderForm.getTableId());
        order.setTable(table);
        order.setCreationTime(LocalDateTime.now());
        order.setBookingTime(creatingOrderForm.getBookingTimeFromFields());
        logger.info("Saving order " + order);
        orderService.save(order);
        return "redirect: success";
    }

    @GetMapping("all")
    public String getOrders(Model model,Principal principalUser) {
        logger.info("Get all orders");
        List<OrderDTO> orders = orderDtoConverter.toDTOList(orderService.getAll());
        model.addAttribute("workerEmail",principalUser.getName());
        model.addAttribute("orders", orders);
        model.addAttribute("tables", tableDtoConverter.toDTOList(tableService.getAll()));
        return "showOrders";
    }

    @GetMapping("ordersForSpecificTable")
    public String getOrdersForSpecificTable(Model model, @RequestParam("tableId") String id,Principal principalUser) {
        if (id.length() == 0 || id.equals("0")) {
            return "redirect: /orders/all";
        } else {
            int tableId = Integer.valueOf(id);
            model.addAttribute("workerEmail",principalUser.getName());
            model.addAttribute("orders", orderDtoConverter.toDTOList(orderService.getByTableId(tableId)));
            model.addAttribute("tables", tableDtoConverter.toDTOList(tableService.getAll()));
        }
        return "showOrders";
    }
    @GetMapping("{id}")
    public String getOrderInfo(Model model, @PathVariable("id") long id){
        Order order = orderService.getById(id);
        OrderDTO  orderDTO = orderDtoConverter.toDTO(order);
        List<DishDTO> dishes = new ArrayList<>();
        int i = 0;
        for (OrderDish orderDish : order.getOrderDish()) {
            dishes.add(dishDtoConverter.toDTO(orderDish.getDish()));
            dishes.get(i).setAmount(orderDish.getAmount());
            i++;
        }
        orderDTO.setDishes(dishes);
        model.addAttribute("order",orderDTO);
        System.out.println(orderDTO);
        return "showInfoAboutOrder";
    }
    @PostMapping("delete/{order_id}")
    public String delete(Model model, @PathVariable("order_id") long orderId){
        logger.info("Deleting order " + orderId);
        orderService.delete(orderId);
        return "redirect: /orders/all";
    }
    @PostMapping("setWorkerForOrder/{order_id}")
    public String setWorkerForOrder(Model model, @PathVariable("order_id") long orderId,Principal principalUser){
        logger.info("Changing worker of order " + orderId);
        Worker worker = workerService.getByEmail(principalUser.getName());
        orderService.changeWorkerForOrder(orderId,worker);
        return "redirect: /orders/all";
    }

    @GetMapping("success")
    public String getSuccessPage() {
        return "success";
    }
}
