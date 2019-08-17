package com.serviceSystem.controller;

import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.Worker;
import com.serviceSystem.entity.dto.DishInOrderDto;
import com.serviceSystem.entity.dto.TableDtoWithSchedule;
import com.serviceSystem.entity.Order;
import com.serviceSystem.entity.dto.DishDto;
import com.serviceSystem.entity.dto.OrderDto;
import com.serviceSystem.service.*;
import com.serviceSystem.service.mapper.DishMapper;
import com.serviceSystem.service.mapper.DishInOrderMapper;
import com.serviceSystem.service.mapper.OrderMapper;
import com.serviceSystem.service.mapper.TableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
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
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private TableMapper tableMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishInOrderMapper dishInOrderMapper;


    @GetMapping("/orders/creating")
    public Map<String, Object> creating() {
        logger.info("Get page for creating order");
        Map<String, Object> response = new HashMap<>();
        List<TableDtoWithSchedule> tables = tableMapper.toListOfTableDtoWithSchedule(tableService.getAll());
        response.put("tables", tables);
        List<DishDto> dishes = dishMapper.toDtoList(dishService.getWhichAreInMenu());
        response.put("dishes", dishes);
        return response;
    }


    @PostMapping("/orders/creating")
    public ResponseEntity save(@RequestBody @Valid OrderDto orderDto, BindingResult bindingResult
            , Principal principalUser) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Order order = orderMapper.toEntity(orderDto);
        Client client = clientService.getByEmail(principalUser.getName());
        order.setClient(client);
        orderService.save(order);
        return new ResponseEntity<>(orderMapper.toDto(order), HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getAll(@RequestParam(value = "table", required = false, defaultValue = "all") String id) {
        List<OrderDto> orders;
        if (id.equals("all")) {
            orders = orderMapper.toDtoList(orderService.getAll());
        } else {
            int tableId = Integer.valueOf(id);
            orders = orderMapper.toDtoList(orderService.getByTableId(tableId));
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("orderId") long id) {
        logger.info("Get info of order " + id);
        OrderDto orderDto = orderMapper.toDto(orderService.getById(id));
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity delete(@PathVariable("orderId") long orderId) {
        logger.info("Deleting order " + orderId);
        orderService.delete(orderId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/orders/active/{orderId}/worker")
    public ResponseEntity setWorkerForOrder(@PathVariable("orderId") long orderId, Principal principalUser) {
        Order order = orderService.getActiveById(orderId);
        Worker worker = workerService.getByEmail(principalUser.getName());
        orderService.changeWorkerForOrder(order, worker);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity update(@RequestBody @Valid OrderDto orderDto, BindingResult bindingResult,
                                 @PathVariable("orderId") long orderId) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Order updatedOrder = orderMapper.toEntity(orderDto);
        Order oldOrder = orderService.getActiveById(orderId);
        oldOrder.setStatus(updatedOrder.getStatus());
        oldOrder.setTable(updatedOrder.getTable());
        oldOrder.setBookingTimeBegin(updatedOrder.getBookingTimeBegin());
        oldOrder.setBookingTimeEnd(updatedOrder.getBookingTimeEnd());
        orderService.update(oldOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/orders/{orderId}/dishes")
    public ResponseEntity updateDishesInOrder(@RequestBody @Valid List<DishInOrderDto> dishesInOrder, BindingResult bindingResult,
                                              @PathVariable("orderId") long orderId) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Order order = orderService.getActiveById(orderId);
        order.setDishesInOrder(dishInOrderMapper.toEntityList(dishesInOrder));
        orderService.updateDishesInOrder(order);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/clients/{clientId}/orders")
    public ResponseEntity<List<OrderDto>> getActiveForClient(@PathVariable("clientId") long clientId,
                                                             @RequestParam(value = "status", required = false, defaultValue = "") String status) {
        logger.info("Get " + status + " orders of client " + clientId);
        if (status.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            List<OrderDto> orders;
            switch (status) {
                case "active":
                    orders = orderMapper.toDtoList(orderService.getActiveOrNotByClientId(clientId, true));
                    return new ResponseEntity<>(orders, HttpStatus.OK);
                case "not_active":
                    orders = orderMapper.toDtoList(orderService.getActiveOrNotByClientId(clientId,false));
                    return new ResponseEntity<>(orders,HttpStatus.OK);
                default:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }
}
