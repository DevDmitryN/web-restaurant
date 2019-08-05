package com.serviceSystem.controller;

import com.serviceSystem.entity.dto.TableDto;
import com.serviceSystem.entity.dto.form.CreatingOrderForm;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.OrderDish;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.entity.dto.DishDto;
import com.serviceSystem.entity.dto.OrderDto;
import com.serviceSystem.service.*;
import com.serviceSystem.service.mapper.DishMapper;
import com.serviceSystem.service.mapper.OrderDishMapper;
import com.serviceSystem.service.mapper.OrderMapper;
import com.serviceSystem.service.mapper.TableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
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
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDishMapper orderDishMapper;
    @Autowired
    private TableMapper tableMapper;
    @Autowired
    private DishMapper dishMapper;


    @GetMapping("/orders/creating")
    public Map<String,Object> creating() {
        logger.info("Get page for creating order");
        Map<String,Object> response = new HashMap<>();
        List<TableDto> tables = tableMapper.toDtoList(tableService.getAll());
        response.put("tables",tables);
        List<DishDto> dishes = dishMapper.toDtoList(dishService.getWhichAreInMenu());
        response.put("dishes",dishes);

        return response;
    }


    @PostMapping("/order/creating")
    public String save(@ModelAttribute("creatingOrderForm") @Valid CreatingOrderForm creatingOrderForm, BindingResult bindingResult
            , Model model, Principal principalUser) {
        orderFormValidator.validate(creatingOrderForm, bindingResult);
        if (bindingResult.hasErrors()) {
            logger.info("Binding result " + bindingResult.getAllErrors());

            return "creatingOrder";
        }
        Order order = new Order();
        List<OrderDish> orderDishes = new ArrayList<>();
        for (DishDto dishDTO : creatingOrderForm.getDishes()) {
//            if (dishDTO.getAmount() != 0) {
//                Dish dish = dishDtoConverter.fromDTO(dishDTO);
//                orderDishes.add(new OrderDish(order, dish, dishDTO.getAmount()));
//            }
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

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getAll(@RequestParam(value = "table", required = false, defaultValue = "all") String id) {
        List<OrderDto> orders;
        if (id.equals("all")) {
            orders = orderService.getAll().stream().map(order -> orderMapper.toDto(order)).collect(Collectors.toList());
        } else {
            int tableId = Integer.valueOf(id);
            orders = orderMapper.toDtoList(orderService.getByTableId(tableId));
        }
        return new ResponseEntity<List<OrderDto>>(orders, HttpStatus.OK);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDto> getOrderInfo(@PathVariable("orderId") long id) {
        logger.info("Get info of order " + id);
        Order order = orderService.getById(id);
        OrderDto orderDto = orderMapper.toDto(order);
        orderDto.setOrderDishDtoList(orderDishMapper.toDtoList(order.getOrderDish()));
        return new ResponseEntity<OrderDto>(orderDto, HttpStatus.OK);
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity delete(@PathVariable("orderId") long orderId) {
        logger.info("Deleting order " + orderId);
        orderService.delete(orderId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

//    @PutMapping("/orders/{order_id}/setWorker")
//    public ResponseEntity setWorkerForOrder(@PathVariable("order_id") long orderId,Principal principalUser) {
//        logger.info("Changing worker of order " + orderId);
//        Worker worker = workerService.getByEmail(principalUser.getName());
//        Order order  = orderService.getById(orderId);
//        if (order == null) {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }else {
//            try {
//                orderService.changeWorkerForOrder(order, worker);
//                return new ResponseEntity(HttpStatus.OK);
//            } catch (OrderAlreadyTakenException e) {
//                return new ResponseEntity(HttpStatus.BAD_REQUEST);
//            }
//        }
//    }

    @GetMapping("/clients/{clientId}/orders")
    public ResponseEntity<List<OrderDto>> getActive(@PathVariable("clientId") long clientId,
                                                    @RequestParam(value = "status", required = false, defaultValue = "") String status) {
        logger.info("Get " + status + " orders of client " + clientId);
        List<OrderDto> orders;
        if (status.isEmpty()) {
            return new ResponseEntity<List<OrderDto>>(HttpStatus.OK);
        } else {
            orders = orderService.getActiveByClientId(clientId).stream().map(
                    order -> {
                        OrderDto dto = orderMapper.toDto(order);
                        dto.setOrderDishDtoList(orderDishMapper.toDtoList(order.getOrderDish()));
                        return dto;
                    }
            ).collect(Collectors.toList());
            return new ResponseEntity<List<OrderDto>>(orders, HttpStatus.OK);
        }
    }
//
//    @PutMapping("/client/{userId}/orders/active/{orderId}/cancel")
//    public ResponseEntity cancelOrder(@PathVariable("orderId") long orderId) {
//        Order order = orderService.getById(orderId);
//        if(order == null){
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        order.setStatus(Status.CANCELLED);
//        orderService.update(order);
//        return new ResponseEntity(HttpStatus.OK);
//    }

}
