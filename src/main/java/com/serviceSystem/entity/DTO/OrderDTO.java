package com.serviceSystem.entity.DTO;

import com.serviceSystem.entity.*;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private Long id;
    private String status;
    private RestaurantTable table;
    private List<Dish> dishes;
    private Client client;
    private Worker worker;
    private String creationTime;
    private String bookingTime;
    private BigDecimal totalPrice;
    public OrderDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RestaurantTable getTable() {
        return table;
    }

    public void setTable(RestaurantTable table) {
        this.table = table;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static List<OrderDTO> toListOfOrderDTO(List<Order> orders){
        List<OrderDTO> ordersDTO = new ArrayList<>();
        orders.forEach( order -> ordersDTO.add(toOrderDTO(order)));
        return ordersDTO;
    }
    public static OrderDTO toOrderDTO(Order order){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(order,OrderDTO.class);
    }
}
