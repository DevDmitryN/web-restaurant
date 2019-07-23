package com.serviceSystem.entity.DTO;

import com.serviceSystem.entity.*;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private Long id;
    private String status;
    private TableDTO table;
    private List<DishDTO> dishes;
    private ClientDTO client;
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

    public TableDTO getTable() {
        return table;
    }

    public void setTable(TableDTO table) {
        this.table = table;
    }

    public List<DishDTO> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishDTO> dishes) {
        this.dishes = dishes;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
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

    public boolean isPossibleToChange(){
        return !(status.equals("Выполнен") || status.equals("Отменен"));
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", table=" + table +
                ", dishes=" + dishes +
                ", client=" + client +
                ", worker=" + worker +
                ", creationTime='" + creationTime + '\'' +
                ", bookingTime='" + bookingTime + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
