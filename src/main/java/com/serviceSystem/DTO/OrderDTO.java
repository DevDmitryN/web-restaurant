package com.serviceSystem.DTO;

import com.serviceSystem.entity.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderDTO {
    String id;
    String table;
    String client;
    String worker;
    String creationTime;
    String bookingTime;

    public OrderDTO(Order order){
        setId(order.getId());
        setClient(order.getClient().getId());
        setWorker(order.getWorker().getId());
        setTable(order.getTable().getId());
        setCreationTime(order.getCreationTime());
        setBookingTime(order.getBookingTime());
    }

    public void setId(long id) {
        this.id = String.valueOf(id);
    }

    public void setTable(int tableId) {
        this.table = String.valueOf(tableId);
    }

    public void setClient(long clientId) {
        this.client = String.valueOf(clientId);
    }

    public void setWorker(long workerId) {
        this.worker = String.valueOf(workerId);
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getId() {
        return id;
    }

    public String getTable() {
        return table;
    }

    public String getClient() {
        return client;
    }

    public String getWorker() {
        return worker;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getBookingTime() {
        return bookingTime;
    }
}
