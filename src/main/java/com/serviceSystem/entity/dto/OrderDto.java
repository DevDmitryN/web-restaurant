package com.serviceSystem.entity.dto;

import com.serviceSystem.entity.*;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {
    private Long id;
    private String status;
    private TableDto table;
    private List<OrderDishDto> orderDishDtoList;
    private ClientDto client;
    private WorkerDto worker;
    private String creationTime;
    private String bookingTime;
    private BigDecimal totalPrice;

    public OrderDto(){

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

    public TableDto getTable() {
        return table;
    }

    public void setTable(TableDto table) {
        this.table = table;
    }

    public List<OrderDishDto> getOrderDishDtoList() {
        return orderDishDtoList;
    }

    public void setOrderDishDtoList(List<OrderDishDto> orderDishDtoList) {
        this.orderDishDtoList = orderDishDtoList;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public WorkerDto getWorker() {
        return worker;
    }

    public void setWorker(WorkerDto worker) {
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


    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", table=" + table +
                ", orderDishDtoList=" + orderDishDtoList +
                ", client=" + client +
                ", worker=" + worker +
                ", creationTime='" + creationTime + '\'' +
                ", bookingTime='" + bookingTime + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
