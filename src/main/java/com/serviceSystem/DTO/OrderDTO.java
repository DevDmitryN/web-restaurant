package com.serviceSystem.DTO;

import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.Dish;
import com.serviceSystem.entity.RestaurantTable;
import com.serviceSystem.entity.Worker;
import com.serviceSystem.entity.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    private Long id;
    private Status status;
    private RestaurantTable table;
    private List<Dish> dishes;
    private Client client;
    private Worker worker;
    private String creationTime;
    private String bookingTime;

    public OrderDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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
}
