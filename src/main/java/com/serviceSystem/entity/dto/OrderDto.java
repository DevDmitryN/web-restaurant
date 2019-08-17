package com.serviceSystem.entity.dto;

import com.serviceSystem.service.validation.ValidateBookingTime;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

//@ValidateBookingTime
public class OrderDto {
    private Long id;
    private String status;
    @NotNull
    private TableDto table;
    @Valid
    private List<DishInOrderDto> dishesInOrder;
    private ClientDto client;
    private WorkerDto worker;
    private String creationTime;
    @NotNull
    @NotBlank(message = "this field can't be blank")
    private String bookingTimeBegin;
    @NotNull
    @NotBlank(message = "this field can't be blank")
    private String bookingTimeEnd;
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

    public List<DishInOrderDto> getDishesInOrder() {
        return dishesInOrder;
    }

    public void setDishesInOrder(List<DishInOrderDto> dishesInOrder) {
        this.dishesInOrder = dishesInOrder;
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

    public String getBookingTimeBegin() {
        return bookingTimeBegin;
    }

    public void setBookingTimeBegin(String bookingTimeBegin) {
        this.bookingTimeBegin = bookingTimeBegin;
    }

    public String getBookingTimeEnd() {
        return bookingTimeEnd;
    }

    public void setBookingTimeEnd(String bookingTimeEnd) {
        this.bookingTimeEnd = bookingTimeEnd;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

}
