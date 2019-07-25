package com.serviceSystem.controller;

import com.serviceSystem.controller.util.CreatingOrderForm;
import com.serviceSystem.controller.util.dtoConverter.DtoConverterImpl;
import com.serviceSystem.controller.util.dtoConverter.DtoConvertrer;
import com.serviceSystem.entity.*;
import com.serviceSystem.entity.DTO.DishDTO;
import com.serviceSystem.entity.DTO.OrderDTO;
import com.serviceSystem.entity.DTO.TableDTO;
import com.serviceSystem.entity.enums.Status;
import com.serviceSystem.exception.OrderAlreadyTakenException;
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


    @GetMapping("/order/creating")
    public String creating(Model model) {
        logger.info("Get page for creating order");
        CreatingOrderForm creatingOrderForm = new CreatingOrderForm();
        creatingOrderForm.setDishes(dishDtoConverter.toDTOList(dishService.getWhichAreInMenu()));
        creatingOrderForm.setTables(tableDtoConverter.toDTOList(tableService.getAll()));
//        creatingOrderForm.setYear(LocalDate.now().getYear());
        model.addAttribute("creatingOrderForm", creatingOrderForm);
        return "creatingOrder";
    }


    @PostMapping("/order/creating")
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
        return "redirect: /order/creating/success";
    }

    @GetMapping("/orders/list")
    public String getAll(Model model, @RequestParam(value = "table",required = false,defaultValue = "all") String id,
                         Principal principalUser,@RequestParam(value = "errorOfTakingOrder",required = false) String error) {
        if(error != null){
            model.addAttribute("error",error);
        }
        if (id.equals("all")) {
//            return "redirect: /orders/all";
            List<OrderDTO> orders = orderDtoConverter.toDTOList(orderService.getAll());
            model.addAttribute("workerEmail", principalUser.getName());
            model.addAttribute("orders", orders);
            model.addAttribute("tables", tableDtoConverter.toDTOList(tableService.getAll()));
        } else {
            int tableId = Integer.valueOf(id);
            model.addAttribute("workerEmail",principalUser.getName());
            model.addAttribute("orders", orderDtoConverter.toDTOList(orderService.getByTableId(tableId)));
            model.addAttribute("tables", tableDtoConverter.toDTOList(tableService.getAll()));
        }
        return "showOrders";
    }
    @GetMapping("/order/{id}")
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
        logger.info("Get info of order " + id);
        return "showInfoAboutOrder";
    }
    @PostMapping("/order/{order_id}/delete")
    public String delete(Model model, @PathVariable("order_id") long orderId){
        logger.info("Deleting order " + orderId);
        orderService.delete(orderId);
        return "redirect: /orders/list";
    }
    @PostMapping("/order/{order_id}/setWorker")
    public String setWorkerForOrder(Model model, @PathVariable("order_id") long orderId,Principal principalUser){
        logger.info("Changing worker of order " + orderId);
        Worker worker = workerService.getByEmail(principalUser.getName());
        try {
            orderService.changeWorkerForOrder(orderId,worker);
        } catch (OrderAlreadyTakenException e) {
            return "redirect: /orders/list?errorOfTakingOrder=true";
        }
        return "redirect: /orders/list";
    }

    @GetMapping("/client/{userId}/orders/active")
    public String getActive(Model model,@PathVariable("userId") long clientId){
        List<Order> orders = orderService.getNotCompletedByClientId(clientId);
        List<OrderDTO> ordersDTO = new ArrayList<>();

        OrderDTO orderDTO;

        for (Order order : orders) {
            int i = 0;
            List<DishDTO> dishes = new ArrayList<>();
            for (OrderDish orderDish : order.getOrderDish()) {
                dishes.add(dishDtoConverter.toDTO(orderDish.getDish()));
                dishes.get(i).setAmount(orderDish.getAmount());
                i++;
            }
            orderDTO = orderDtoConverter.toDTO(order);
            orderDTO.setDishes(dishes);
            ordersDTO.add(orderDTO);
        }
        model.addAttribute("orders",ordersDTO);
        return "activeOrders";
    }

    @PostMapping("/client/{userId}/orders/active/{orderId}/cancel")
    public String cancelOrder(Model model,@PathVariable("orderId") long orderId,@PathVariable("userId") long clientId){
        Order order = orderService.getById(orderId);
        order.setStatus(Status.CANCELLED);
        orderService.update(order);
        return "/client/"+ clientId +"/orders/active";
    }

    @GetMapping("/order/creating/success")
    public String getSuccessPage() {
        return "success";
    }
}
